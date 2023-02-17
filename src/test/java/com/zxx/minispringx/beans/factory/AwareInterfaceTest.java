package com.zxx.minispringx.beans.factory;

import com.zxx.minispringx.beans.factory.service.HelloService;
import com.zxx.minispringx.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class AwareInterfaceTest {


    @Test
    public void test() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        HelloService helloService = context.getBean("helloService", HelloService.class);
        System.out.println("helloService的applicationContext: " +  helloService.getApplicationContext());
        System.out.println("helloService的beanFactory" + helloService.getBeanFactory());
    }
}
