package com.zxx.minispringx.beans.factory;

import com.zxx.minispringx.beans.factory.beans.Person;
import com.zxx.minispringx.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class ApplicationContextTest {

    @Test
    public void testApplicationContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.refresh();
        Person person = (Person) context.getBean("person");
        System.out.println("姓名：" + person.getName());
        System.out.println("年龄：" + person.getAge());
        System.out.println("开什么车：" + person.getCar().getBrand());
    }
}
