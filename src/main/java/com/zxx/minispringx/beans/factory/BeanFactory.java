package com.zxx.minispringx.beans.factory;

import com.zxx.minispringx.beans.BeansException;

public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;
}
