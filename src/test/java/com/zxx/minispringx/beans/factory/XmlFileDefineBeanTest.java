package com.zxx.minispringx.beans.factory;

import com.zxx.minispringx.beans.factory.beans.Person;
import com.zxx.minispringx.beans.factory.support.DefaultListableBeanFactory;
import com.zxx.minispringx.beans.factory.xml.XmlBeanDefinitionReader;
import org.junit.Test;

public class XmlFileDefineBeanTest {

    @Test
    public void testXmlFile() throws Exception {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        Person person = (Person) factory.getBean("person");
        System.out.println("姓名：" + person.getName());
        System.out.println("年龄：" + person.getAge());
        System.out.println("开什么车：" + person.getCar().getBrand());

    }
}
