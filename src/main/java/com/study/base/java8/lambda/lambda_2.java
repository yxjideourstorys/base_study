package com.study.base.java8.lambda;

import com.study.base.java8.lambda.i.MyFun;

/**
 * 二、Lambda 表达式需要“函数式接口”的支持
 *      函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。可以使用注解 @FunctionalInterface 修饰，可以检查是否是函数式接口(接口中有多个方法会编译报错)
 */
public class lambda_2 {

    public void lambdaTest1(){
        Integer result = operation(100, x -> x * 2 + 1);
        System.out.println("result：" + result);
    }

    private static Integer operation(Integer num, MyFun myFun){
        return myFun.getValue(num);
    }

}
