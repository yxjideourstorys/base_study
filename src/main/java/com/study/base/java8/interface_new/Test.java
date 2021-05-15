package com.study.base.java8.interface_new;

public class Test {
    public static void main(String[] args) {
        // 有继承先找继承的
        SubClass sub1 = new SubClass();
        System.out.println(sub1.getName());

        // 如果无继承，但多实现并且每一个接口中都有默认方法getName()时，在实现的接口中必须重定getName()方法，来说明使用的是哪个接口的方法。
        SubClass2 sub2 = new SubClass2();
        System.out.println(sub2.getName());

        MyFun.show();
    }
}
