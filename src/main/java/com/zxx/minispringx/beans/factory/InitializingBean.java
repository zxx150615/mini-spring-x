package com.zxx.minispringx.beans.factory;

import com.zxx.minispringx.beans.BeansException;

/**
 * 初始化的Bean接口
 */
public interface InitializingBean {

    void afterPropertiesSet() throws BeansException;
}
