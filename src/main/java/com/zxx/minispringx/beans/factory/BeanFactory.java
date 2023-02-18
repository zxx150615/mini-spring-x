package com.zxx.minispringx.beans.factory;

import com.zxx.minispringx.beans.BeansException;

public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
