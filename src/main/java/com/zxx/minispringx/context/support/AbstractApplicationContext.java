package com.zxx.minispringx.context.support;

import com.zxx.minispringx.beans.BeansException;
import com.zxx.minispringx.beans.factory.ConfigurableListableBeanFactory;
import com.zxx.minispringx.beans.factory.config.BeanFactoryPostProcessor;
import com.zxx.minispringx.beans.factory.config.BeanPostProcessor;
import com.zxx.minispringx.beans.factory.config.ConfigurableBeanFactory;
import com.zxx.minispringx.context.ApplicationContext;
import com.zxx.minispringx.context.ApplicationEvent;
import com.zxx.minispringx.context.ApplicationListener;
import com.zxx.minispringx.context.ConfigurableApplicationContext;
import com.zxx.minispringx.context.event.ApplicationEventMulticaster;
import com.zxx.minispringx.context.event.ContextClosedEvent;
import com.zxx.minispringx.context.event.ContextRefreshedEvent;
import com.zxx.minispringx.context.event.SimpleApplicationEventMulticaster;
import com.zxx.minispringx.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    private static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME ="applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        // 应用上下文的刷新动作
        // 1.先刷新Bean工厂
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 把ApplicationContextPostProcessor,让继承自ApplicationContextAware的Bean能够感知Bean
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 应用上下文，在实例化之前，先执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);

        // 之后，先在其他bean实例化之前注册BeanPostProcessor
        registerBeanPostProcessor(beanFactory);

        // 初始化事件发布者
        initApplicationEventMulticaster();

        //注册事件监听器
        registerListeners();

        //提前实例化Bean
        beanFactory.preInstantiateSingletons();

        //发布容器刷新完成事件
        finishRefresh();
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }


    @Override
    public void publishEvent(ApplicationEvent event){
        applicationEventMulticaster.multicastEvent(event);
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeanOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.addSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
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
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

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

    public void registerShutdownHook() {
        Thread shutdownHook = new Thread() {
            public void run() {
                doClose();
            }
        };
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }


    public void close() {
        doClose();
    }

    protected void doClose() {
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));
        destroyBeans();
    }

    protected void destroyBeans() {
        getBeanFactory().destroySingletons();
    }

}
