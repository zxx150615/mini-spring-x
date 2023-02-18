package com.zxx.minispringx.context.event;

import com.zxx.minispringx.context.ApplicationContext;
import com.zxx.minispringx.context.ApplicationEvent;

public abstract class ApplicationContextEvent extends ApplicationEvent {
    
    public ApplicationContextEvent(ApplicationContext source) {
        super(source);
    }
    
    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
    
}
