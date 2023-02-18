package com.zxx.minispringx.context.event;

import java.util.HashSet;

import com.zxx.minispringx.beans.factory.BeanFactory;
import com.zxx.minispringx.beans.factory.BeanFactoryAware;
import com.zxx.minispringx.context.ApplicationListener;

import java.util.Set;

public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware{

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new HashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        
    }    
    
}
