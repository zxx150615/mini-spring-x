package com.zxx.minispringx.beans.factory;

import com.zxx.minispringx.beans.BeansException;

public interface DisposableBean {

    void destroy() throws Exception;

}
