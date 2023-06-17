package com.study.base.design_pattern.dynamic_proxy.cglib;

public class CglibDynamicProxyTest {
    public static void main(String[] args) {
        HelloService helloService = (HelloService) new CglibProxy().newProxy(new HelloService());
        helloService.sayHello();
    }
}
