package com.study.base.design_pattern.dynamic_proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    private final AdviceCglib advice = new AdviceCglib();

    public Object newProxy(Object target){
        Enhancer enhancer = new Enhancer();
        // cglib 主要根据继承来生成代码的关系
        // 设置父类
        enhancer.setSuperclass(target.getClass());
        // 设置回调  就是代理类通过  MethodInterceptor 找到 intercept方法进行回调
        enhancer.setCallback(this);
        // crate 方法
        // 第一步、生成源代码
        // 第二步、编译成class文件
        // 第三步、加载到JVM中，并返回被代理对象
        return enhancer.create();
    }

    /**
     * @param o 代理对象
     * @param method    委托类方法
     * @param objects   方法参数
     * @param methodProxy   代理方法的MethodProxy对象。每个被代理的方法都对应一个MethodProxy对象
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        advice.before();
        // 切记不要写成 methodProxy.invoke(obj,args);  这里面就会存在循环调用的问题，这里会调用cglib代理类，然后循环调用
         methodProxy.invokeSuper(o, objects);
        advice.after();

        return null;
    }
}
