package com.zxx.minispringx.beans.factory.support;


import com.zxx.minispringx.beans.BeansException;
import com.zxx.minispringx.beans.factory.config.BeanDefinition;

/**
 * 填充策略
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
