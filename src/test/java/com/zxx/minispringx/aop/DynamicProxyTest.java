package com.zxx.minispringx.aop;

import com.zxx.minispringx.aop.Interceptor.WorldServiceInterceptor;
import com.zxx.minispringx.aop.aspectj.AspectJExpressionPointcut;
import com.zxx.minispringx.aop.framework.CglibAopProxy;
import com.zxx.minispringx.aop.framework.JdkDynamicAopProxy;
import com.zxx.minispringx.beans.factory.service.WorldService;
import com.zxx.minispringx.beans.factory.service.WorldServiceImpl;
import org.junit.Before;
import org.junit.Test;

public class DynamicProxyTest {

    AdvisedSupport advisedSupport = new AdvisedSupport();

    @Before
    public void testDynamicProxy(){
        // 主体类
        WorldService worldService = new WorldServiceImpl();
        // 配置目标节点
        TargetSource targetSource = new TargetSource(worldService);
        WorldServiceInterceptor worldServiceInterceptor = new WorldServiceInterceptor();
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.zxx.minispringx.beans.factory.service.WorldService.explode(..))").getMethodMatcher();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodMatcher(methodMatcher);
        advisedSupport.setMethodInterceptor(worldServiceInterceptor);
//        WorldService proxy = (WorldService)new JdkDynamicAopProxy(advisedSupport).getProxy();
//        proxy.explode();
    }

    @Test
    public void testJdkDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testCglibDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }
}
