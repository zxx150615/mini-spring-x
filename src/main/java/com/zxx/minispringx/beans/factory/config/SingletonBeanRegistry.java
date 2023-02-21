package com.zxx.minispringx.beans.factory.config;

public interface SingletonBeanRegistry {

    Object getSingleBean(String name);

    void addSingleton(String beanName, Object singletonObject);
}
