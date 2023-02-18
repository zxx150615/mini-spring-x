package com.zxx.minispringx.beans.factory.config;

import com.zxx.minispringx.beans.factory.BeanFactory;
import com.zxx.minispringx.beans.factory.HierarchicalBeanFactory;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {


    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();
}
