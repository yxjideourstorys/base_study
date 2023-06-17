package com.study.base.design_pattern.observer_mode;

public class ObserverTest {

    public static void main(String[] args) {
        MySubject subject = new Cat();

        // 订阅
        subject.attach(new Dog());
        subject.attach(new Mouse());

        // 通知
        subject.cry();
    }
}
