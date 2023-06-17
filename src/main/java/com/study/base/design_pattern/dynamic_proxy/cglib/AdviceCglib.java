package com.study.base.design_pattern.dynamic_proxy.cglib;

public class AdviceCglib {
    public void before(){
        System.out.println("对核心业务方法执行前的增强");
    }

    public void after(){
        System.out.println("对核心业务方法执行后的增强");
    }
}
