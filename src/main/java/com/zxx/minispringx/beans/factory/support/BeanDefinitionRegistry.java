package com.zxx.minispringx.beans.factory.support;

import com.zxx.minispringx.beans.BeansException;
import com.zxx.minispringx.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    // 注册BeanDefinition信息
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);


    // 根据BeanName查询对应的BeanDefinition
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    // 查询对应的BeanDefinition是否存在于注册器中
    Boolean containsBeanDefinition(String beanName);

    // 返回所有的Bean定义信息
    String[] getBeanDefinitionNames();

}
