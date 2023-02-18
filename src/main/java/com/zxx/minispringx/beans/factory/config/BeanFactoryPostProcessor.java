package com.zxx.minispringx.beans.factory.config;

import com.zxx.minispringx.beans.BeansException;
import com.zxx.minispringx.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {

    //在所有BeanDefintion加载完成后，但在bean实例化之前，提供修改BeanDefinition属性值的机制
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;


}
