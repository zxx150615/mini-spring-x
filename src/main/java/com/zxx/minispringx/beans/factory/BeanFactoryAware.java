package com.zxx.minispringx.beans.factory;

import com.zxx.minispringx.beans.BeansException;

public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
