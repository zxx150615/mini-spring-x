package com.zxx.minispringx.beans.factory.common.event;

import com.zxx.minispringx.context.ApplicationListener;
import com.zxx.minispringx.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println(this.getClass().getName());
    }
}
