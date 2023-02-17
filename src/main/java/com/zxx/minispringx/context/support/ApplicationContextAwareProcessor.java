package com.zxx.minispringx.context.support;

import com.zxx.minispringx.beans.BeansException;
import com.zxx.minispringx.beans.factory.Aware;
import com.zxx.minispringx.beans.factory.config.BeanPostProcessor;
import com.zxx.minispringx.context.ApplicationContext;
import com.zxx.minispringx.context.ApplicationContextAware;

public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
