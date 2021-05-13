package com.study.base.java8.lambda;

import com.study.base.java8.lambda.i.Exercise2;
import com.study.base.java8.lambda.i.Exercise3;
import com.study.base.java8.lambda.vo.Employee;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *  1、调用 Collections.sort() 方法，通过定制排序比较两个 Employee (先按年龄比，年龄相同按姓名比)，使用 Lambda 作为参数传递
 *
 *  2、a.声明函数式接口，接口中声明抽象方法，public String getValue(String str);
 *     b.声明类 TestLambda, 类中编写方法使用接口作为参数，将一个字符串转成大写，并作为方法的返回值。
 *     c.再将一个字符串的第2个和第4个索引位置进行截取子串。
 *
 *  3、a.声明一个带两个泛型的函数式接口，泛型类为<T, R> T为参数，R为返回值
 *     b.接口中声明对应抽象方法
 *     c.在TestLambda类中声明方法，使用接口作为参数，计算两个long类型的和
 *     d.再计算两个long类型参数的乘积
 */
public class lambda_exercise {

    List<Employee> emps = Arrays.asList(
            new Employee("小红", 18),
            new Employee("小拍", 19),
            new Employee("小白", 20),
            new Employee("小东", 17)
    );

    /**
     * 1、调用 Collections.sort() 方法，通过定制排序比较两个 Employee (先按年龄比，年龄相同按姓名比)，使用 Lambda 作为参数传递
     */
    public void lambdaTest1(){
        Collections.sort(emps, (x, y) -> {
            if (x.getAge() == y.getAge()){
                return x.getName().compareTo(y.getName());
            }else {
                return Integer.compare(x.getAge(), y.getAge());
            }
        });

        emps.forEach(x -> System.out.println(x));
    }

    /**
     * 2、a.声明函数式接口，接口中声明抽象方法，public String getValue(String str);
     *    b.声明类 TestLambda, 类中编写方法使用接口作为参数，将一个字符串转成大写，并作为方法的返回值。
     *    c.再将一个字符串的第2个和第4个索引位置进行截取子串。
     */
    public void lambdaTest2(){
        String str = "magical dgg";
        String res1 = exchange(str, (x) -> StringUtils.upperCase(x));
        System.out.println(res1);
        String res2 = exchange(str, (x) -> StringUtils.substring(x, 2, 5));
        System.out.println(res2);
    }
    private String exchange(String str, Exercise2 ex2){
        return ex2.getValue(str);
    }


    /**
     * 3、a.声明一个带两个泛型的函数式接口，泛型类为<T, R> T为参数，R为返回值
     *    b.接口中声明对应抽象方法
     *    c.在TestLambda类中声明方法，使用接口作为参数，计算两个long类型的和
     *    d.再计算两个long类型参数的乘积
     */
    public void lambdaTest3(){
        Long res1 = count(100L, 200L, (x, y) -> x + y);
        Long res2 = count(100L, 200L, (x, y) -> x * y);
        System.out.println("和：" + res1 + "\n积：" + res2);
    }
    private Long count(Long l1, Long l2, Exercise3<Long, Long> ex3){
        return ex3.getValue(l1, l2);
    }
}
