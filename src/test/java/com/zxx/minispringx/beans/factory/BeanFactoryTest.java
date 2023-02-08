package com.zxx.minispringx.beans.factory;

import org.junit.Test;

import static org.junit.Assert.*;

public class BeanFactoryTest {


    @Test
    public void getBean() {
        BeanFactory factory = new BeanFactory();
        factory.registerBean("hello", new HelloService());
        HelloService helloService = (HelloService)factory.getBean("hello");
        helloService.sayHello();

    }

    class HelloService {
        public String sayHello() {
            System.out.println("hello");
            return "hello";
        }
    }
}