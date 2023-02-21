package com.zxx.minispringx.beans.factory.common.event;

import com.zxx.minispringx.context.ApplicationListener;

public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println(this.getClass().getName());
    }
}
