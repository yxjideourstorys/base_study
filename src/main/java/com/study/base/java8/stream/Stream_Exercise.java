package com.study.base.java8.stream;

import com.study.base.java8.vo.Employee;
import com.study.base.java8.vo.Trader;
import com.study.base.java8.vo.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  1、给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
 *      给定：【1, 2, 3, 4, 5】 应返回：【1, 4, 9, 16, 25】
 *
 *  2、怎么用 map 和 reduce 方法来数一数流中有多少个 Employee呢？
 *
 *  3、a.找出2011年发生的所有交易，并按交易额排序(从低到高)
 *     b.交易员都在哪些不同的城市工作过？
 *     c.查找所有来自剑桥的交易员，并按姓名排序
 *     d.返回所有交易员的姓名字符串，按字母顺序排序
 *     e.有没有交易员是在米兰工作的？
 *     f.打印生活在剑桥的交易员的所有交易额
 *     g.所有交易中，最高的交易额是多少？
 *     h.找到交易额最小的交易
 */
public class Stream_Exercise {



    /**
     * 1、给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
     *       给定：【1, 2, 3, 4, 5】 应返回：【1, 4, 9, 16, 25】
     */
    public void exercise1(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> results = list.stream()
                .map(x -> x * x)
                .collect(Collectors.toList());
        results.forEach(System.out::println);
    }


    /**
     * 2、怎么用 map 和 reduce 方法来数一数流中有多少个 Employee呢？
     */
    static List<Employee> emps = Arrays.asList(
            new Employee("小红", 18, 2000.0, Employee.Status.FREE),
            new Employee("小拍", 19, 1000.0, Employee.Status.VOCATION),
            new Employee("小白", 20, 8200.0, Employee.Status.BUSY),
            new Employee("小白", 20, 8100.0, Employee.Status.FREE),
            new Employee("小黑", 28, 7000.0, Employee.Status.BUSY),
            new Employee("小黑", 22, 6500.0, Employee.Status.VOCATION),
            new Employee("小东", 17, 6000.0, Employee.Status.BUSY)
    );
    public void exercise2(){
        Integer sum = emps.stream()
                .map(x -> 1)
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }


    /**
     * 3、a.找出2011年发生的所有交易，并按交易额排序(从低到高)
     *    b.交易员都在哪些不同的城市工作过？
     *    c.查找所有来自剑桥的交易员，并按姓名排序
     *    d.返回所有交易员的姓名字符串，按字母顺序排序
     *    e.有没有交易员是在米兰工作的？
     *    f.打印生活在剑桥的交易员的所有交易额
     *    g.所有交易中，最高的交易额是多少？
     *    h.找到交易额最小的交易
     */
    List<Transaction> transactions = null;
    public void exercise3(){
        Trader raoul = new Trader("raoul", "Cambridge");
        Trader mario = new Trader("mario", "Milan");
        Trader alan = new Trader("alan", "Cambridge");
        Trader brian = new Trader("brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

}
