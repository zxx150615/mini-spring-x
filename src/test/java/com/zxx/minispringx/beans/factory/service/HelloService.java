package com.zxx.minispringx.beans.factory.service;

import com.zxx.minispringx.beans.BeansException;
import com.zxx.minispringx.beans.factory.BeanFactory;
import com.zxx.minispringx.beans.factory.BeanFactoryAware;
import com.zxx.minispringx.context.ApplicationContext;
import com.zxx.minispringx.context.ApplicationContextAware;

public class HelloService implements ApplicationContextAware, BeanFactoryAware {

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

}
