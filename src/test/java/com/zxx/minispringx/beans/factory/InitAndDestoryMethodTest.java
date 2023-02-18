package com.zxx.minispringx.beans.factory;

import com.zxx.minispringx.beans.factory.beans.Car;
import com.zxx.minispringx.beans.factory.beans.Person;
import com.zxx.minispringx.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class InitAndDestoryMethodTest {

    @Test
    public void testInitAndDestroyMethod() throws InterruptedException {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");


        Person person = (Person) applicationContext.getBean("person");
        System.out.println("姓名：" + person.getName());

        applicationContext.registerShutdownHook();
        System.out.println("容器关闭");

        Thread.sleep(4000);
        Person person1 = (Person) applicationContext.getBean("person");
        System.out.println("姓名：" + person1.getName());
    }
}
