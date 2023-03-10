package com.zxx.minispringx.aop;

import java.lang.reflect.Method;

public interface MethodMatcher {

    boolean match(Method method, Class<?> targetClass);

}
