package com.study.base.design_pattern.observer_mode;

public class Cat extends MySubject{

    @Override
    public void cry() {
        System.out.println("猫叫！");
        System.out.println("-------------------");

        for (Object obs: observers) {
            ((MyObserver)obs).response();
        }
    }
}
