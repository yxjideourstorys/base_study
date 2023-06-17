package com.study.base.design_pattern.observer_mode;

public class Mouse implements MyObserver {
    @Override
    public void response() {
        System.out.println("老鼠努力逃跑！");
    }
}
