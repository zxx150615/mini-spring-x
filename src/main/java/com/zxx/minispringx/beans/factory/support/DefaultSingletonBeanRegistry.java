package com.zxx.minispringx.beans.factory.support;

import com.zxx.minispringx.beans.BeansException;
import com.zxx.minispringx.beans.factory.DisposableBean;
import com.zxx.minispringx.beans.factory.config.SingletonBeanRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    Map<String, Object> singleBeanMap = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleBean(String name) {
        return singleBeanMap.get(name);
    }

    public void addSingleton(String name, Object bean) {
        singleBeanMap.put(name, bean);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        ArrayList<String> beanNames = new ArrayList<>(disposableBeans.keySet());
        for (String beanName : beanNames) {
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
