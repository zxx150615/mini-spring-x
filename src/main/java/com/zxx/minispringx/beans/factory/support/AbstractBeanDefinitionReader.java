package com.zxx.minispringx.beans.factory.support;

import com.zxx.minispringx.core.io.DefaultResourceLoader;
import com.zxx.minispringx.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    private ResourceLoader loader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader loader) {
        this.registry = registry;
        this.loader = loader;
    }

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }


    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return loader;
    }

    @Override
    public void loadBeanDefinitions(String[] locations) {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.loader = resourceLoader;
    }

}
