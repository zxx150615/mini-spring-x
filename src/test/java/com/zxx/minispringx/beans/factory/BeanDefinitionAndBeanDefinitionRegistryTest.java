package com.zxx.minispringx.beans.factory;

import com.zxx.minispringx.beans.factory.config.BeanDefinition;
import com.zxx.minispringx.beans.factory.support.CglibSubclassingInstantiationStrategy;
import com.zxx.minispringx.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

public class BeanDefinitionAndBeanDefinitionRegistryTest {

    @Test
    public void testBeanFactory() throws Exception {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.setInstantiationStrategy(new CglibSubclassingInstantiationStrategy());
//        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
//        factory.registryBeanDefinition("helloService", beanDefinition);
//        HelloService helloService = (HelloService) factory.getBean("helloService");
//        helloService.sayHello();
    }
}
