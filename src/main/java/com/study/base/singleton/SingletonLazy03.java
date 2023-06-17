package com.study.base.singleton;

/**
 * 懒汉式-线程安全-双检锁的方式
 */
public class SingletonLazy03 {

    private volatile static SingletonLazy03 instance;

    private SingletonLazy03(){}

    public static SingletonLazy03 getInstance(){
        if (instance == null){
            synchronized (SingletonLazy03.class){
                if (instance == null){
                    instance = new SingletonLazy03();
                }
            }
        }
        return instance;
    }
}
