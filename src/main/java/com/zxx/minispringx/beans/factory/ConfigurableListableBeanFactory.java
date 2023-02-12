package com.zxx.minispringx.beans.factory;

import com.zxx.minispringx.beans.factory.config.AutowireCapableBeanFactory;
import com.zxx.minispringx.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

public interface ConfigurableListableBeanFactory
        extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 根据名称查找BeanDefinition
     *
     * @param beanName
     * @return
     * @throws BeansException 如果找不到BeanDefintion
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}
