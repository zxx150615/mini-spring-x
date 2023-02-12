package com.zxx.minispringx.beans.factory.common;

import com.zxx.minispringx.beans.BeansException;
import com.zxx.minispringx.beans.factory.beans.Car;
import com.zxx.minispringx.beans.factory.config.BeanPostProcessor;

public class CustomerBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行BeanPostProcessor前置处理方法");
        if ("car".equals(beanName)) {
            ((Car) bean).setBrand("比亚迪");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行BeanPostProcessor后置处理方法");
        return bean;
    }
}
