package com.study.base.design_pattern.dynamic_proxy.cglib;

public class CglibDynamicProxyTest {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        HelloService helloService = (HelloService) cglibProxy.newProxy(new HelloService());
        helloService.sayHello();
    }
}
