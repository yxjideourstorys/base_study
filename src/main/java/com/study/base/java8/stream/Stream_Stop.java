package com.study.base.java8.stream;

import com.study.base.java8.vo.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *  3、终止操作(终端操作)
 *      a.查找与匹配
 *          allMatch --- 检查是否匹配所有元素（流中每一个元素都要匹配到, 才会返回true）
 *          anyMatch --- 检查是否至少匹配一个元素 （流中只要匹配到一个元素, 就返回true）
 *          noneMatch --- 检查是否没有匹配所有元素（与allMatch相反, 一个匹配不到, 返回true）
 *          findFirst --- 返回第一个元素
 *                        返回的是一个Optional 里面封装了一个防止非空的方法，防止排序后再找第一个元素时，找不到而报空指针异常
 *          findAny --- 返回当前流中的任意元素
 *          count --- 返回流中元素的总个数
 *          max --- 返回流中最大值
 *          min --- 返回流中最小值
 *
 *      b.归约
 *          reduce(T identity, BinaryOperator) / reduce(BinaryOperator) --- 可以将流中元素反复结合起来，得到一个值
 *
 */
public class Stream_Stop {

    static List<Employee> emps = Arrays.asList(
            new Employee("小红", 18, 2000, Employee.Status.FREE),
            new Employee("小拍", 19, 1000, Employee.Status.VOCATION),
            new Employee("小白", 20, 8200, Employee.Status.BUSY),
            new Employee("小白", 20, 8100, Employee.Status.FREE),
            new Employee("小黑", 28, 7000, Employee.Status.BUSY),
            new Employee("小黑", 22, 6500, Employee.Status.VOCATION),
            new Employee("小东", 17, 6000, Employee.Status.BUSY)
    );

    public void StreamMatchTest(){
        // allMatch 检查是否匹配所有元素（流中每一个元素都要匹配到, 才会返回true）
        boolean allMatch = emps.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(allMatch);

        // anyMatch 检查是否至少匹配一个元素 （流中只要匹配到一个元素, 就返回true）
        boolean anyMatch = emps.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(anyMatch);

        // noneMatch 检查是否没有匹配所有元素（与allMatch相反, 一个匹配不到, 返回true）
        boolean noneMatch = emps.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(noneMatch);

        // findFirst 返回第一个元素  返回的是一个Optional 里面封装了一个防止非空的方法，防止排序后再找第一个元素时，找不到而报空指针异常
        Optional<Employee> findFirst = emps.stream()
                .sorted((x, y) -> Integer.compare(x.getSalary(), y.getSalary()))
                .findFirst();
        System.out.println(findFirst.get());

        // findAny 返回当前流中的任意元素
        Optional<Employee> findAny = emps.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(findAny.get());

        // count 返回流中元素的总个数
        long count = emps.stream()
                .count();
        System.out.println(count);

        // max/min 返回流中最大值/最小值
        Optional<Employee> max = emps.stream().max((x, y) -> Integer.compare(x.getSalary(), y.getSalary()));
        System.out.println(max.get());

        // 获取emps中，工资最少是多少
        Optional<Integer> salaryMin = emps.stream().map(Employee::getSalary).min(Integer::compare);
        System.out.println(salaryMin.get());
    }
}
