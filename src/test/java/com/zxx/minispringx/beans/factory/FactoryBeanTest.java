package com.zxx.minispringx.beans.factory;

import org.junit.Test;
import com.zxx.minispringx.beans.factory.beans.Car;
import com.zxx.minispringx.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanTest{

    @Test
    public void testFactoryBean() throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");
        Car car =context.getBean("car",Car.class);
        System.out.print("car的品牌" + car.getBrand());
    }
}