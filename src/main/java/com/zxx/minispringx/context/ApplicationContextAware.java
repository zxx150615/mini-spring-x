package com.zxx.minispringx.context;

import com.zxx.minispringx.beans.BeansException;

public interface ApplicationContextAware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
