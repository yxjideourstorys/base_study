package com.study.base.java8.interface_new;

public class SubClass2 /**extends MyClass*/ implements MyFun, MyFun2{

    @Override
    public String getName(){
        return MyFun2.super.getName();
    }
}
