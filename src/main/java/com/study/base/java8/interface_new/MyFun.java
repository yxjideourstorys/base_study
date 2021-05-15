package com.study.base.java8.interface_new;

public interface MyFun {

    default String getName(){
        return "哈哈";
    }

    public static void show(){
        System.out.println("接口MyFun中的静态方法");
    }
}
