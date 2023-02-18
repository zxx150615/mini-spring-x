package com.zxx.minispringx.beans.factory.support;

import com.zxx.minispringx.beans.BeansException;
import com.zxx.minispringx.beans.factory.BeanFactory;
import com.zxx.minispringx.beans.factory.FactoryBean;
import com.zxx.minispringx.beans.factory.config.BeanDefinition;
import com.zxx.minispringx.beans.factory.config.BeanPostProcessor;
import com.zxx.minispringx.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();

    @Override
    public Object getBean(String beanName) throws BeansException {
        // 先从单例注册器里面寻找
        Object sharedInstance = getSingleBean(beanName);
        if (sharedInstance != null) {
            // 当bean不为空的话，如果是FactoryBean，则从FactoryBean中创建Bean(也就是如果是用户自定义的Bean，则调用FactoryBean.getBean())
            return getObjectForBeanInstance(sharedInstance, beanName);
        }

        BeanDefinition definition = getBeanDefinition(beanName);
        Object bean = createBean(beanName, definition);
        return getObjectForBeanInstance(bean, beanName);
    }

    /**
     * 如果是FactoryBean，则直接从Bean中创建Bean，即使用FactoryBean中的Bean
     * @param beanInstance
     * @param beanName
     * @return
     */
    protected Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        Object object = beanInstance;
        if (object instanceof FactoryBean) {
            FactoryBean factoryBean = (FactoryBean) object;
            try {
                // 如果是单例的FactoryBean，则直接从缓存中获取
                if (factoryBean.isSingleton()) {
                    object = factoryBeanObjectCache.get(beanName);
                    if (object == null) {
                        object = factoryBean.getObject();
                        this.factoryBeanObjectCache.put(beanName, object);
                    } 
                } else {
                    // 该Bean是prototype类型,直接创建
                    object = factoryBean.getObject();
                }
            } catch (Exception ex) {
                throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", ex);
            }
        } 
        // 如果不是FactoryBean,则直接返回Bean本身
        return object;
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws org.springframework.beans.BeansException {
        return ((T) getBean(name));
    }

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
