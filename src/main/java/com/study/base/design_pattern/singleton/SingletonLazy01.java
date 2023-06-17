package com.study.base.design_pattern.singleton;

/**
 * 懒汉式-线程不安全
 */
public class SingletonLazy01 {
    // 构造器私有
    private SingletonLazy01(){}

    private static SingletonLazy01 instance = null;

    public static SingletonLazy01 getInstance(){
        if (instance == null){
            instance = new SingletonLazy01();
        }
        return instance;
    }
}
