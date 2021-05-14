package com.study.base.java8.stream;

import com.study.base.java8.vo.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *   2、中间操作
 *       a.筛选与切片
 *            filter --- 接收 Lambda，从流中排除某些元素。
 *            limit --- 截断流，使其元素不超过给定数是。
 *            skip(n) --- 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流，与limit(n) 互补。
 *            distinct --- 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素。
 *
 *       b.映射
 *            map --- 接收 Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会应用到每个元素上，并将其映射成一个新的元素。
 *            flatMap ---  接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
 *
 *            注意：
 *                由下面的需求得出：
 *                    map 会把得到的流作为参数，放到一个流中 { {a,a,a},{b,b,b}... }
 *                    latMap 会把流里面的流的每一个元素，放到一个流中 { a,a,a,b,b,b... }
 *                可以类比，add()  和  addAll()
 *
 *       c.排序
 *            sorted() --- 自然排序(compareable 里的 compareTo方法)
 *            sorted(Comparator com) ---定制排序(comparator)
 */
public class Stream_2_MidOpt {

    static List<Employee> emps = Arrays.asList(
            new Employee("小红", 18, 2000.0),
            new Employee("小拍", 19, 1000.0),
            new Employee("小白", 20, 8200.0),
            new Employee("小白", 20, 8100.0),
            new Employee("小黑", 28, 7000.0),
            new Employee("小黑", 22, 6500.0),
            new Employee("小东", 17, 6000.0)
    );

    /**
     * 中间操作
     *    a.筛选与切片
     *      filter --- 接收 Lambda，从流中排除某些元素
     *      limit --- 截断流，使其元素不超过给定数是
     *      skip(n) --- 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流，与limit(n) 互补
     *      distinct --- 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     */
    public void streamFilterTest(){
        // filter
        // “中单操作!”文字会输出多次：这叫内部迭代，迭代操作由 Stream API 完成
        Stream<Employee> streamFilter = emps.stream().filter((e) -> {
            System.out.println("中间操作!");
            return e.getAge() > 35;
        });

        // 终止操作
        streamFilter.forEach(System.out::println);
    }

    public void streamLimitTest(){
        // limit
        Stream<Employee> streamLimit = emps.stream().filter((e) -> {
            System.out.println("短路!");
            return e.getSalary() > 5000;
        }).limit(2);

        // 终止操作
        streamLimit.forEach(System.out::println);
    }

    public void streamSkipTest(){
        // skip
        Stream<Employee> streamSkip = emps.stream().filter((e) -> {
            System.out.println("跳过!");
            return e.getSalary() > 5000;
        }).skip(2);

        // 终止操作
        streamSkip.forEach(System.out::println);
    }

    public void streamDistinctTest(){
        // distinctD
        Stream<Employee> streamDistinct = emps.stream().filter((e) -> {
            System.out.println("去重!");
            return e.getSalary() > 5000;
        }).distinct();

        // 终止操作
        streamDistinct.forEach(System.out::println);
    }

    /**
     * 中间操作
     *     映射
     *          map --- 接收 Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会应用到每个元素上，并将其映射成一个新的元素。
     *          flatMap ---  接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
     */
    public void streamMapTest(){
        // map
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        // list.stream().map((str) -> str.toUpperCase()).forEach(System.out::println);
        list.stream().map(String::toUpperCase).forEach(System.out::println);

        System.out.println("-----------------------------------------------------------");

        emps.stream().map(Employee::getName).forEach(System.out::println);
    }

    /**
     *  需求：解析一个字符串，然后把解析出来的数据，一个一个放到集合中
     */
    public void mapTest(){
        // 使用map 完成需求
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        Stream<Stream<Character>> stream = list.stream().map(Stream_2_MidOpt::filterCharacter);
        // { {a,a,a},{b,b,b}... }
        stream.forEach((x) -> x.forEach(System.out::println));
    }
    private static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();

        for (Character chars : str.toCharArray()) {
            list.add(chars);
        }
        return list.stream();
    }

    public void streamFlatMapTest(){
        // 使用flatMap 完成需求
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        list.stream().flatMap(Stream_2_MidOpt::filterCharacter).forEach(System.out::println);
    }


    /**
     *  c.排序
     *        sorted() --- 自然排序(comparable 里的 compareTo方法)
     *        sorted(Comparator com) ---定制排序(comparator)
     */
    public void streamSortTest(){
        // sorted()
        emps.stream().sorted().forEach(System.out::println);

        System.out.println("----------------------------------");

        // sorted(Comparator com)
        emps.stream().sorted((x, y) -> {
            if (x.getAge().compareTo(y.getAge()) == 0){
                return x.getName().compareTo(y.getName());
            }else {
                return x.getAge().compareTo(y.getAge());
            }
        }).forEach(System.out::println);
    }
}
