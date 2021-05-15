package com.study.base.java8.interface_new;

public interface MyFun2 {

    default String getName(){
        return "嘿嘿";
    }

    public static void show(){
        System.out.println("接口MyFun2中的静态方法");
    }
}
