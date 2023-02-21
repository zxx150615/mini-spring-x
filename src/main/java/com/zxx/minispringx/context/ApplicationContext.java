package com.zxx.minispringx.context;


import com.zxx.minispringx.beans.factory.HierarchicalBeanFactory;
import com.zxx.minispringx.beans.factory.ListableBeanFactory;
import com.zxx.minispringx.core.io.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
