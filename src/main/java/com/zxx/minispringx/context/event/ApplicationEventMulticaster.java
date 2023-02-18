package com.zxx.minispringx.context.event;

import com.zxx.minispringx.context.ApplicationEvent;
import com.zxx.minispringx.context.ApplicationListener;

public interface ApplicationEventMulticaster {

    /**
     * 容器事件观察者
     */
    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);
    
    void multicastEvent(ApplicationEvent event);
    
}
