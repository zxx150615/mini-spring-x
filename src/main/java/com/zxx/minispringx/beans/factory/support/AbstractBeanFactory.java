package com.zxx.minispringx.beans.factory.support;

import com.zxx.minispringx.beans.BeansException;
import com.zxx.minispringx.beans.factory.BeanFactory;
import com.zxx.minispringx.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
        // 先从单例注册器里面寻找
        Object bean = getSingleBean(beanName);
        if (bean != null) {
            return bean;
        }

        BeanDefinition definition = getBeanDefinition(beanName);
        return createBean(beanName, definition);
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
