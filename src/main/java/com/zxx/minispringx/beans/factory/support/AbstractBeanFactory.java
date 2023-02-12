package com.zxx.minispringx.beans.factory.support;

import com.zxx.minispringx.beans.BeansException;
import com.zxx.minispringx.beans.factory.BeanFactory;
import com.zxx.minispringx.beans.factory.config.BeanDefinition;
import com.zxx.minispringx.beans.factory.config.BeanPostProcessor;
import com.zxx.minispringx.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

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

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        // 有则覆盖
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }
}
