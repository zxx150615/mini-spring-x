package com.zxx.minispringx.beans.factory.xml;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.zxx.minispringx.beans.BeansException;
import com.zxx.minispringx.beans.PropertyValue;
import com.zxx.minispringx.beans.factory.config.BeanDefinition;
import com.zxx.minispringx.beans.factory.config.BeanReference;
import com.zxx.minispringx.beans.factory.support.AbstractBeanDefinitionReader;
import com.zxx.minispringx.beans.factory.support.BeanDefinitionRegistry;
import com.zxx.minispringx.core.io.Resource;
import com.zxx.minispringx.core.io.ResourceLoader;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader loader) {
        super(registry, loader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            InputStream inputStream = resource.getInputStream();
            try {
                doLoadBeanDefinitions(inputStream);
            } finally {
                inputStream.close();
            }
        } catch (IOException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }

    }

    protected void doLoadBeanDefinitions(InputStream inputStream) {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNotes = root.getChildNodes();
        for (int i = 0; i < childNotes.getLength(); i++) {
            if (childNotes.item(i) instanceof Element) {
                if (BEAN_ELEMENT.equals(((Element) childNotes.item(i)).getNodeName())) {
                    // 现在解析到Bean这一层
                    Element bean = (Element) childNotes.item(i);
                    String id = bean.getAttribute(ID_ATTRIBUTE);
                    String name = bean.getAttribute(NAME_ATTRIBUTE);
                    String className = bean.getAttribute(CLASS_ATTRIBUTE);
                    Class<?> clazz = null;
                    try {
                        clazz = Class.forName(className);
                    } catch (ClassNotFoundException e) {
                        throw new BeansException("Cannot find class [" + className + "]");
                    }
                    // Id优先级高于Name
                    String beanName = !StringUtils.isEmpty(id) ? id : name;
                    // 如果都没有，则取className第一个字母小写
                    if (StringUtils.isEmpty(beanName)) {
                        beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                    }
                    // 开始组装BeanDefinition
                    BeanDefinition beanDefinition = new BeanDefinition(clazz);
                    // 解析Bean下属性注入
                    for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                        if (bean.getChildNodes().item(j) instanceof Element) {
                            if (PROPERTY_ELEMENT.equals(((Element) bean.getChildNodes().item(j)).getNodeName())) {
                                Element property = (Element) bean.getChildNodes().item(j);
                                String nameAttribute = property.getAttribute(NAME_ATTRIBUTE);
                                String valAttribute = property.getAttribute(VALUE_ATTRIBUTE);
                                String refAttribute = property.getAttribute(REF_ATTRIBUTE);
                                if (StringUtils.isEmpty(nameAttribute)) {
                                    throw new BeansException("The name attribute cannot be null or empty");
                                }
                                Object value = valAttribute;
                                // 如果存在Bean引用
                                if (!StringUtils.isEmpty(refAttribute)) {
                                    value = new BeanReference(refAttribute);
                                }
                                PropertyValue propertyValue = new PropertyValue(nameAttribute, value);
                                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                            }
                        }
                    }
                    if (getRegistry().containsBeanDefinition(beanName)) {
                        // BeanName不允许重名
                        throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
                    }
                    // 往BeanDefinition注册器中注册这个解析好之后的BeanDefinition
                    getRegistry().registryBeanDefinition(beanName, beanDefinition);
                }
            }
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        Resource resource = getResourceLoader().getResource(location);
        this.loadBeanDefinitions(resource);
    }
}
