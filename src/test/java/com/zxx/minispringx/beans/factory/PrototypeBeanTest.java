package com.zxx.minispringx.beans.factory;

import com.zxx.minispringx.beans.factory.beans.Car;
import com.zxx.minispringx.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class PrototypeBeanTest {

    @Test
    public void testPrototypeBean() throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:prototype-spring.xml");
        Car car1 = context.getBean("car", Car.class);
        Car car2 = context.getBean("car", Car.class);
        Boolean isSame = car1 == car2;
        System.out.println("是否是同一个呢？" + isSame);
    }

}
