package com.study.base.java8.lambda;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java 8 内置的四大核心函数式接口
 *
 *    一、Consumer<T> ：消费型接口
 *          void accept(T t);
 *
 *    二、supplier<T> ：供给型接口
 *          T get();
 *
 *    三、Function<T, R> ：函数型接口
 *          R apply(T t);
 *
 *    四、Predicate<T> ：断言型接口
 *          boolean test(T t);
 */
public class lambda_3 {

    /**
     * 消费型接口
     *    花钱消费
     */
    public void lambdaTest1(){
        play(5000, (x) -> System.out.println("东哥哥花了" + x + "元大吃大喝！"));
    }
    private void play(double money, Consumer<Double> con){
        con.accept(money);
    }

    /**
     * 供给型接口
     *    随机生成100个数，保存到一个list中，并返回
     */
    public void lambdaTest2(){
        List<Integer> results = strRandom(100, () -> (int) (Math.random() * 100));
        results.forEach((x) -> System.out.println(x));
    }
    private List<Integer> strRandom(int num, Supplier<Integer> su){
        List<Integer> lists = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            lists.add(su.get());
        }
        return lists;
    }

    /**
     * 函数型接口
     *     处理字符串
     */
    public void lambdaTest3(){
        String res = strHandler("我是神奇的东哥哥", (str) -> StringUtils.substring(str, 2, 5));
        System.out.println(res);
    }
    private String strHandler(String str, Function<String, String> fun){
        return fun.apply(str);
    }


    /**
     * 断言型接口
     *     将满足条件的字符串，放入集合中
     */
    public void lambdaTest4(){
        List<String> params = Arrays.asList("qqqa", "www", "eeea", "rrra");

        List<String> newLists = filterStr(params, (x) -> {
            if (x.contains("a")) {
                return true;
            }
            return false;
        });

        newLists.forEach((x) -> System.out.println(x));
    }

    private List<String> filterStr(List<String> strs, Predicate<String> pre){
        List<String> lists = new ArrayList<>();

        strs.forEach((x) -> {
            if (pre.test(x)){
                lists.add(x);
            }
        });
        return lists;
    }

}
