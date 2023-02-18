package com.zxx.minispringx.context.event;

import com.zxx.minispringx.context.ApplicationContext;

public class ContextRefreshedEvent extends ApplicationContextEvent{
 
    public ContextRefreshedEvent(ApplicationContext source) {
        super(source);
    }

}
