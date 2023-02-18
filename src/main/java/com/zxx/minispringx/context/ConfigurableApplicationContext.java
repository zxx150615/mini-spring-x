package com.zxx.minispringx.context;

import com.zxx.minispringx.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     */
    void refresh() throws BeansException;


    /**
     * 关闭应用上下文
     */
    void close();

    /**
     * 往虚拟机中注册一个钩子，确保在虚拟机关闭之前，关闭容器
     */
    void registerShutdownHook();


}
