package com.zxx.minispringx.context;

public interface ApplicationEventPublisher {

    /**
     * 发布事件
     */
    void publishEvent(ApplicationEvent event);
    
}
