package com.study.base.design_pattern.singleton;

/**
 * 懒汉式-静态内部类-线程安全
 */
public class SingletonLazy04 {

    /**
     * 私有构造方法,防止被实例化
     */
    private SingletonLazy04(){}

    /**
     * 使用内部类维护单例
     */
    private static class SinglethonFactory{
        private static SingletonLazy04 instance = new SingletonLazy04();
    }

    public static SingletonLazy04 getInstance(){
        return SinglethonFactory.instance;
    }
}
