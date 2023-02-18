package com.zxx.minispringx.beans.factory;

import com.zxx.minispringx.beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {
    /**
     * 返回指定类型的所有实例
     *
     * @param type
     * @param <T>
     * @return
     * @throws org.springframework.beans.BeansException
     */
    <T> Map<String, T> getBeanOfType(Class<T> type) throws BeansException;

    /**
     * 返回定义的所有bean的名称
     *
     * @return
     */
    String[] getBeanDefinitionNames();
}
