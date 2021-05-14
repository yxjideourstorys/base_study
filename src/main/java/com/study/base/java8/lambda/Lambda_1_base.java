package com.study.base.java8.lambda;

import com.study.base.java8.lambda.i.MyFun;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 一、Lambda 表达式的基础语法：Java8中引入 了一个新的操作符 “->” 该操作符称为箭头操作符或Lambda操作符
 *      左侧：Lambda 表达式的参数
 *      右侧：Lambda 表达式中所需要执行的功能，即Lambda 体
 *
 *      语法格式一：无参数，无返回值
 *          () -> System.out.println("Hello Lambda!")
 *
 *      语法格式二：有一个参数，并且无返回值
 *          (x) -> System.out.println(x)
 *
 *      语法格式三：若只有一个参数，小括号可以省略不写  （一般都会加上小括号）
 *          x -> System.out.println(x)
 *
 *      语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
 *          Comparator<Integer> com = (x, y) -> {
 *             System.out.println("函数式接口");
 *             return Integer.compare(x, y);
 *         };
 *
 *      语法格式五：若 Lambda 体中只有一条语句，return和大括号都可以省略不写
 *          Comparator<Integer> com1 = (x, y) -> Integer.compare(x, y);
 *
 *      语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器可以通过上下文推断出数据类型，即“类型推断”。
 *          (Integer x, Integer y) -> Integer.compare(x, y);    <==>    (x, y) -> Integer.compare(x, y);
 *
 * 二、Lambda 表达式需要“函数式接口”的支持
 *       函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。可以使用注解 @FunctionalInterface 修饰，
 *                  可以检查是否是函数式接口(接口中有多个方法会编译报错)
 */
public class Lambda_1_base {

    /**
     * 语法一
     */
    public void lambdaTest1(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        };
        r.run();

        System.out.println("-----------Lambda写法--------------");

        Runnable r1 = () -> System.out.println("Hello Lambda!");
        r1.run();
    }

    /**
     * 语法二
     */
    public void lambdaTest2(){
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("东哥哥NB");

        // 语法三
        Consumer<String> con1 = x -> System.out.println(x);
        con1.accept("东哥哥更NB");
    }

    /**
     * 语法四
     */
    public void lambdaTest3(){
        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };

        // 语法五
        Comparator<Integer> com1 = (x, y) -> Integer.compare(x, y);

        // 语法六
        Comparator<Integer> com2 = (Integer x, Integer y) -> Integer.compare(x, y);
    }

    /**
     * 函数式接口
     */
    public void lambdaTest4(){
        Integer result = operation(100, x -> x * 2 + 1);
        System.out.println("result：" + result);
    }

    private static Integer operation(Integer num, MyFun myFun){
        return myFun.getValue(num);
    }


}
