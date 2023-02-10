package com.zxx.minispringx.beans.factory.support;

import com.zxx.minispringx.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
