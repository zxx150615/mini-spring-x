package com.zxx.minispringx.beans.factory.support;

import com.zxx.minispringx.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    Map<String, Object> singleBeanMap = new HashMap<>();
    @Override
    public Object getSingleBean(String name) {
        return singleBeanMap.get(name);
    }

    protected void addSingleton(String name, Object bean) {
        singleBeanMap.put(name, bean);
    }
}
