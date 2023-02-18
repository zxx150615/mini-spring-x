package com.zxx.minispringx.context.event;

import com.zxx.minispringx.context.ApplicationContext;

public class ContextClosedEvent extends ApplicationContextEvent{

    /**
     * 容器关闭事件
     */

    public ContextClosedEvent(ApplicationContext source){
        super(source);
    }
    
}
