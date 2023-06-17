package com.study.base.design_pattern.dynamic_proxy.jdk;

/**
 * jdk动态代理
 */
public class JdkDynamicProxyTest {

    public static void main(String[] args) {
        ProxyHandler proxyHandler = new ProxyHandler();
        TargetInterface proxy = (TargetInterface) proxyHandler.getProxy(new Target());
        proxy.coreWork();
    }
}
