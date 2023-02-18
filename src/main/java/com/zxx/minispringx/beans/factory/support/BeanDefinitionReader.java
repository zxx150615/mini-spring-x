package com.zxx.minispringx.beans.factory.support;

import com.zxx.minispringx.beans.factory.config.BeanDefinition;
import com.zxx.minispringx.core.io.Resource;
import com.zxx.minispringx.core.io.ResourceLoader;

public interface BeanDefinitionReader {

    // Bean的定义信息读取器，能够将资源读取为Bean定义信息,并注册到BeanDefintiion注册器中
    // 所以需要有BeanDefinition注册器，同时还要有资源加载器
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    // 从资源中读取并加载BeanDefinition
    void loadBeanDefinitions(Resource resource);

    void loadBeanDefinitions(String location);

    void loadBeanDefinitions(String[] locations);
}
