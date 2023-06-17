package com.study.base.design_pattern.singleton;

/**
 * 懒汉式-线程安全
 */
public class SingletonLazy02 {

    private static SingletonLazy02 instance;

    // 构造器私有化
    private SingletonLazy02(){}

    public synchronized static SingletonLazy02 getInstance(){
        if (instance == null){
            instance = new SingletonLazy02();
        }
        return instance;
    }
}
