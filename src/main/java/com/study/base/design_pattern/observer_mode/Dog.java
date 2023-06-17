package com.study.base.design_pattern.observer_mode;

public class Dog implements MyObserver{
    @Override
    public void response() {
        System.out.println("狗跟着叫！");
    }
}
