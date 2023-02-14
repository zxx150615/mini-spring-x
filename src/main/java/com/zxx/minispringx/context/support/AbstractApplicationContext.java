package com.zxx.minispringx.context.support;

import com.zxx.minispringx.beans.BeansException;
import com.zxx.minispringx.beans.factory.ConfigurableListableBeanFactory;
import com.zxx.minispringx.beans.factory.config.BeanFactoryPostProcessor;
import com.zxx.minispringx.beans.factory.config.BeanPostProcessor;
import com.zxx.minispringx.context.ConfigurableApplicationContext;
import com.zxx.minispringx.core.io.DefaultResourceLoader;

import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    @Override
    public void refresh() throws BeansException {
        // 应用上下文的刷新动作
        // 1.先刷新Bean工厂
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 应用上下文，在实例化之前，先执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);

        // 之后，先在其他bean实例化之前注册BeanPostProcessor
        registerBeanPostProcessor(beanFactory);

        //提前实例化Bean
        beanFactory.preInstantiateSingletons();
    }

    // 注册BeanPostProcessor也是一样的道理，主要是将各种BeanPostProcessor给注册到BeanFactory里面去就可以了。
    protected void registerBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeanOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    // 直接从Bean工厂中获取BeanFactoryPostProcessor，并且取之于民，用之于民
    protected void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        // 因为BeanFactoryProcessor已经注册到BeanFactory去了
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeanOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor processor : beanFactoryPostProcessorMap.values()) {
            processor.postProcessBeanFactory(beanFactory);
        }
    }

    protected abstract void refreshBeanFactory() throws BeansException;


    @Override
    public <T> Map<String, T> getBeanOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeanOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanFactory().getBean(beanName);
    }

    public abstract ConfigurableListableBeanFactory getBeanFactory();
}
