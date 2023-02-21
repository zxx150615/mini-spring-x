package com.zxx.minispringx.beans.factory.common.event;

import com.zxx.minispringx.context.ApplicationListener;
import com.zxx.minispringx.context.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(this.getClass().getName());
    }
}
