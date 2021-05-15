package com.study.base.java8.stream;

import com.study.base.java8.vo.Employee;
import com.study.base.java8.vo.Trader;
import com.study.base.java8.vo.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public void exercise3(){
        Trader raoul = new Trader("raoul", "Cambridge");
        Trader mario = new Trader("mario", "Milan");
        Trader alan = new Trader("alan", "Cambridge");
        Trader brian = new Trader("brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Trader> traders = new ArrayList<>();
        traders.add(raoul);
        traders.add(mario);
        traders.add(alan);
        traders.add(brian);

        // a.找出2011年发生的所有交易，并按交易额排序(从低到高)
        List<Transaction> a = transactions.stream()
                .filter(x -> x.getYear() == 2011)
                .sorted((x, y) -> x.getValue() - y.getValue())
                .collect(Collectors.toList());
        a.forEach(System.out::println);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // b.交易员都在哪些不同的城市工作过？
        List<String> b = traders.stream()
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        b.forEach(System.out::println);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // c.查找所有来自剑桥的交易员，并按姓名排序
        List<Trader> c = traders.stream()
                .filter(x -> "Cambridge".equals(x.getCity()))
                .sorted((x, y) -> x.getName().compareTo(y.getName()))
                .collect(Collectors.toList());
        c.forEach(System.out::println);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // d.返回所有交易员的姓名字符串，按字母顺序排序
        List<Character> d = traders.stream()
                .map(Trader::getName)
                .flatMap(Stream_Exercise::exchangeChar)
                .sorted(Character::compareTo).collect(Collectors.toList());
        d.forEach(System.out::println);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // e.有没有交易员是在米兰工作的？
        boolean e = traders.stream()
                .map(Trader::getCity)
                .anyMatch("Milan"::equals);
        System.out.println(e);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // f.打印生活在剑桥的交易员的所有交易额
        List<String> traderList = traders.stream()
                .filter(x -> "Cambridge".equals(x.getCity()))
                .map(Trader::getName)
                .collect(Collectors.toList());
        Integer f = transactions.stream()
                .filter(x -> traderList.contains(x.getTrader().getName()))
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);
        System.out.println(f);
        // 优化1
        Integer f2 = transactions.stream()
                .filter(x -> (traders.stream()
                        .filter(y -> "Cambridge".equals(y.getCity()))
                        .map(Trader::getName)
                        .collect(Collectors.toList()))
                        .contains(x.getTrader().getName()))
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);
        System.out.println(f2);
        // 优化2
        List<String> traderList2 = traders.stream()
                .filter(x -> "Cambridge".equals(x.getCity()))
                .map(Trader::getName)
                .collect(Collectors.toList());
        Integer f3 = transactions.stream()
                .filter(x -> traderList.contains(x.getTrader().getName()))
        // .collect(Collectors.summingInt(Transaction::getValue));
                .mapToInt(Transaction::getValue).sum();
        System.out.println(f3);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // g.所有交易中，最高的交易额是多少？
        Optional<Integer> g = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compare);
        System.out.println(g.get());

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // h.找到交易额最小的交易
        Optional<Transaction> h = transactions.stream()
                .min((x, y) -> Integer.compare(x.getValue(), y.getValue()));
        System.out.println(h.get());
    }
    private static Stream<Character> exchangeChar(String str){
        List<Character> list = new ArrayList<>();

        for (Character chars: str.toCharArray()){
            list.add(chars);
        }
        return list.stream();
    }
}
