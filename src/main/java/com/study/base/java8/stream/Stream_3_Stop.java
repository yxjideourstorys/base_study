package com.study.base.java8.stream;

import com.study.base.java8.vo.Employee;

import java.util.*;
import java.util.stream.Collectors;

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
 *          reduce(T identity, BinaryOperator)     --- 可以将流中元素反复结合起来，得到一个值。  T为起始值、BinaryOperator为结合运算
 *          reduce(BinaryOperator)      --- 可以将流中元素反复结合起来，得到一个值，返回的是Operator。
 *
 *          注意：
 *              map与reduce的连接通常称为 “map-reduce” 模式，因Google用它来进行网络搜索而出名
 *
 *      c.收集
 *          collect(Collector c) --- 将流转换为其他形式。接收一个 Collector 接口的实现，用于给Stream中的元素做汇总的方法
 *
 *          注意：
 *              Collector 接口中方法的实现决定了如何对流执行收集操作（如收集到List、Set、Map)。但是 Collectors 实现类提供了很多
 *              静态方法，可以方便地创建常见收集器实例。
 *
 */
public class Stream_3_Stop {

    static List<Employee> emps = Arrays.asList(
            new Employee("小红", 18, 2000.0, Employee.Status.FREE),
            new Employee("小拍", 19, 1000.0, Employee.Status.VOCATION),
            new Employee("小白", 20, 8200.0, Employee.Status.BUSY),
            new Employee("小白", 20, 8100.0, Employee.Status.FREE),
            new Employee("小黑", 28, 7000.0, Employee.Status.BUSY),
            new Employee("小黑", 22, 6500.0, Employee.Status.VOCATION),
            new Employee("小东", 17, 6000.0, Employee.Status.BUSY)
    );

    /**
     *  a.查找与匹配
     *       allMatch --- 检查是否匹配所有元素（流中每一个元素都要匹配到, 才会返回true）
     *       anyMatch --- 检查是否至少匹配一个元素 （流中只要匹配到一个元素, 就返回true）
     *       noneMatch --- 检查是否没有匹配所有元素（与allMatch相反, 一个匹配不到, 返回true）
     *       findFirst --- 返回第一个元素
     *                     返回的是一个Optional 里面封装了一个防止非空的方法，防止排序后再找第一个元素时，找不到而报空指针异常
     *       findAny --- 返回当前流中的任意元素
     *       count --- 返回流中元素的总个数
     *       max --- 返回流中最大值
     *       min --- 返回流中最小值
     */
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
                .sorted((x, y) -> Double.compare(x.getSalary(), y.getSalary()))
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
        Optional<Employee> max = emps.stream().max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
        System.out.println(max.get());

        // 获取emps中，工资最少是多少
        Optional<Double> salaryMin = emps.stream().map(Employee::getSalary).min(Double::compare);
        System.out.println(salaryMin.get());
    }

    /**
     *  b.归约
     *       reduce(T identity, BinaryOperator)     --- 可以将流中元素反复结合起来，得到一个值。  T为起始值、BinaryOperator为结合运算
     *       reduce(BinaryOperator)      --- 可以将流中元素反复结合起来，得到一个值，返回的是Operator。
     */
    public void streamReduceTest(){
        // reduce(T identity, BinaryOperator)
        List<Integer> list = Arrays.asList(1, 2, 34, 5, 6, 7);
        Integer reduce1 = list.stream()
                .reduce(0, Integer::sum);
        System.out.println(reduce1);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // reduce(BinaryOperator)
        Optional<Double> reduce2 = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);

        System.out.println(reduce2.get());
    }

    /**
     *  c.收集
     *      collect(Collector c) --- 将流转换为其他形式。接收一个 Collector 接口的实现，用于给Stream中的元素做汇总的方法
     */
    public void streamCollectTest(){
        // list
        List<String> list = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        // set
        Set<String> set = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);

        // HashSet
        HashSet<String> hashSet = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);

        // 总数
        Long count = emps.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        // 平均值
        Double average = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(average);

        // 总和
        Double sum = emps.stream().
                collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);

        // 最大值
        Optional<Double> max = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compare));
        System.out.println(max.get());


        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


        // 另外一种获取 average count max min sum 的操作
        DoubleSummaryStatistics dss = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getAverage());
        System.out.println(dss.getMax());
        System.out.println(dss.getMin());
        System.out.println(dss.getCount());
        System.out.println(dss.getSum());


        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


        // 分组
        Map<Employee.Status, List<Employee>> group = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(group);

        // 多级分组
        Map<Employee.Status, Map<String, List<Employee>>> muchGroup = emps.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(x -> {
            if (x.getAge() <= 35) {
                return "青年";
            } else if (x.getAge() <= 50) {
                return "中年";
            } else {
                return "老年";
            }
        })));
        System.out.println(muchGroup);


        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


        // 分片/分区        满足条件的一个区，不满足条件的一个区
        Map<Boolean, List<Employee>> partition = emps.stream()
                .collect(Collectors.partitioningBy(x -> x.getSalary() > 6000));
        System.out.println(partition);


        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


        // 字符串拼接
        String join = emps.stream()
                .map(Employee::getName)
//                .collect(Collectors.joining(","));
                .collect(Collectors.joining(",", "首 ", " 尾"));
        System.out.println(join);
    }
}
