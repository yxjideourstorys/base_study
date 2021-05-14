package com.study.base.java8.lambda;

import com.study.base.java8.vo.Employee;

import java.util.Comparator;
import java.util.function.*;

/**
 * 一、方法引用：若 Lambda 体中的内容有方法已经实现了，我们可以使用“方法引用”
 *          (可以理解为方法引用是 Lambda 表达式的另外一种表现形式)
 *
 *      主要有三种语法格式：
 *          1：
 *               对象::实例方法名
 *
 *          2：
 *               类::静态方法名
 *
 *          3：
 *               类::实例方法名
 *
 *        注意：实现接口的抽象方法的参数列表和返回值类型，必须与方法引用的方法的参数列表和返回值类型保持一致
 *
 *  二、构造器引用：
 *      格式：
 *          ClassName::new
 *
 *  三、数组引用：
 *      格式：
 *          Type[]::new
 */
public class Lambda_3_MethodRef {

    /**
     * 对象::实例方法名
     */
    public void methodRefTest1(){
        // example 1
        Employee emp = new Employee();
        Supplier<String> sup = () -> emp.getName();
        String str = sup.get();
        System.out.println(str);

        // 对象::实例方法名
        Supplier<Integer> sup2 = emp::getAge;
        Integer num = sup2.get();
        System.out.println(num);


        // example 2
        Consumer<String> con = (x) -> System.out.println(x);
        Consumer<String> con2 = System.out::println;
    }

    /**
     * 类::静态方法名
     */
    public void methodRefTest2(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> com2 = Integer::compare;

        Function<String, Integer> fun = (x) -> Integer.parseInt(x);
        Function<String, Integer> fun2 = Integer::parseInt;
    }

    /**
     * 类::实例方法名
     *      若 Lambda 参数列表中的第一个参数是 实例方法的调用者，而第二个参数是实例参数时，可以使用 ClassName::method
     *      若 Lambda 的参数只有一个，当这个参数是实例方法的调用者时，可以使用 ClassName::method
     */
    public void methodRefTest3(){
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        BiPredicate<String, String> bp2 = String::equals;

        BiFunction<String, String, Integer> bf = (x, y) -> x.compareTo(y);
        BiFunction<String, String, Integer> bf2 = String::compareTo;
    }


    /**
     * 构造器引用
     *  调用哪个构造器，年参数个数
     */
    public void constructorRefTest4(){
        Supplier<Employee> sup = () -> new Employee();

        // 构造器引用
        // 调用空参
        Supplier<Employee> sup2 = Employee::new;
        Employee employee = sup2.get();
        System.out.println(employee);

        // 调用1个参数构造器
        Function<Integer, Employee> fun = (x) -> new Employee(x);
        Function<Integer, Employee> fun2 = Employee::new;
        Employee apply = fun2.apply(10);
        System.out.println(apply);

        // 调用2个参数的构造器
        BiFunction<String, String, Employee> fun3 = (x, y) -> new Employee();
        BiFunction<String, Integer, Employee> fun4 = Employee::new;
        Employee apply2 = fun4.apply("哈哈", 10);
        System.out.println(apply2);
    }

    /**
     * 数组引用
     */
    public void ArrayRefTest5(){
        Function<Integer, String[]> fun = (x) -> new String[x];
        String[] str = fun.apply(10);
        System.out.println(str);

        System.out.println("-------------------------------");

        Function<Integer, String[]> fun2 = String[]::new;
        String[] apply = fun2.apply(20);
        System.out.println(apply);
    }

}
