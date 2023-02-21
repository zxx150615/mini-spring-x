package com.zxx.minispringx.beans.factory.common.event;

import com.zxx.minispringx.context.ApplicationContext;
import com.zxx.minispringx.context.event.ApplicationContextEvent;

public class CustomEvent extends ApplicationContextEvent {
    public CustomEvent(ApplicationContext source) {
        super(source);
    }
}
