package com.study.base.design_pattern.dynamic_proxy.jdk;

/**
 * 目标类
 */
public class Target implements TargetInterface{
    @Override
    public void coreWork() {
        System.out.println("===核心业务方法运行===");
    }
}
