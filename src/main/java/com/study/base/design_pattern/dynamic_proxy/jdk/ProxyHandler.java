package com.study.base.design_pattern.dynamic_proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler implements InvocationHandler {
    private Object target;
    // 增强类
    private final AdviceJdk adviceJdk = new AdviceJdk();

    public Object getProxy(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        adviceJdk.before();
        Object invoke = method.invoke(target, args);
        adviceJdk.after();

        return invoke;
    }
}
