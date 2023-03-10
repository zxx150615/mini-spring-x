package com.zxx.minispringx.aop;

import com.zxx.minispringx.aop.Aspectj.AspectJExpressionPointcut;
import com.zxx.minispringx.beans.factory.service.HelloService;
import org.junit.Test;

import java.lang.reflect.Method;

public class PointcutExpressionTest {

    @Test
    public void testPointCutExpression() throws Exception{
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.zxx.minispringx.beans.factory.service.HelloService.*(..))");
        Class<HelloService> clazz = HelloService.class;
        Method method = clazz.getDeclaredMethod("sayHello");
        System.out.println(pointcut.match(method, clazz));
        System.out.println(pointcut.matches(clazz));
    }
}
