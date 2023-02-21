package com.zxx.minispringx.beans.factory;

import com.zxx.minispringx.beans.factory.common.event.CustomEvent;
import com.zxx.minispringx.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class EventAndEventListenerTest {

    @Test
    public void testEventListener() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:event-and-event-listener.xml");
        context.publishEvent(new CustomEvent(context));
        context.registerShutdownHook();
    }
}
