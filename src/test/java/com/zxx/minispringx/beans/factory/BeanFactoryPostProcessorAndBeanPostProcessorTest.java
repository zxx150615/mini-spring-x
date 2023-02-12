package com.zxx.minispringx.beans.factory;

import com.zxx.minispringx.beans.factory.beans.Person;
import com.zxx.minispringx.beans.factory.common.CustomBeanFactoryPostProcessor;
import com.zxx.minispringx.beans.factory.common.CustomerBeanPostProcessor;
import com.zxx.minispringx.beans.factory.config.BeanFactoryPostProcessor;
import com.zxx.minispringx.beans.factory.support.DefaultListableBeanFactory;
import com.zxx.minispringx.beans.factory.xml.XmlBeanDefinitionReader;
import org.junit.Test;

public class BeanFactoryPostProcessorAndBeanPostProcessorTest {

    @Test
    public void testBeanFactoryPostProcessor() throws Exception {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        BeanFactoryPostProcessor beanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(factory);
        Person person = (Person)factory.getBean("person");
        System.out.println("姓名：" + person.getName());
        System.out.println("年龄：" + person.getAge());
        System.out.println("开什么车：" + person.getCar().getBrand());
    }

    @Test
    public void testBeanPostProcessor() throws Exception{
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.addBeanPostProcessor(new CustomerBeanPostProcessor());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        Person person = (Person)factory.getBean("person");
        System.out.println("姓名：" + person.getName());
        System.out.println("年龄：" + person.getAge());
        System.out.println("开什么车：" + person.getCar().getBrand());
    }
}
