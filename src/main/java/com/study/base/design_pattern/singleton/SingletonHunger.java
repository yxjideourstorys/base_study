package com.study.base.design_pattern.singleton;

/**
 * 饿汉式-线程安全
 */
public class SingletonHunger {

    // 私有实例，在类初始化时就加载
    private static SingletonHunger instance = new SingletonHunger();

    // 私有构造方法，禁止其他程序创建该类的对象
    private SingletonHunger(){}

    // 对外提供变量的公共访问方式
    public static SingletonHunger getInstance(){
        return instance;
    }
}
