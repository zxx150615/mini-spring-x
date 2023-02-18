package com.zxx.minispringx.beans.factory.common;

import com.zxx.minispringx.beans.BeansException;
import com.zxx.minispringx.beans.PropertyValue;
import com.zxx.minispringx.beans.PropertyValues;
import com.zxx.minispringx.beans.factory.ConfigurableListableBeanFactory;
import com.zxx.minispringx.beans.factory.config.BeanDefinition;
import com.zxx.minispringx.beans.factory.config.BeanFactoryPostProcessor;
import com.zxx.minispringx.beans.factory.config.ConfigurableBeanFactory;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    /**
     * 自定义BeanFactory后置处理器
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //先拿到指定的BeanDefinition
        BeanDefinition beanDefinition = (BeanDefinition) beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","曾祥鑫2号"));
    }
}
