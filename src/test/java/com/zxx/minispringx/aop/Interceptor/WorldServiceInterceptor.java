package com.zxx.minispringx.aop.Interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class WorldServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("执行之前打一下日志");
        Object result = methodInvocation.proceed();
        System.out.println("执行之后打一下日志");
        return result;
    }
}
