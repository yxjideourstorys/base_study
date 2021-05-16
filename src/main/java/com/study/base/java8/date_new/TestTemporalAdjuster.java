package com.study.base.java8.date_new;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 *      TemporalAdjuster --- 时间校正器   函数式接口
 *          有时我们可能需要获取例如：将日期调整到“下个周日”等操作
 *
 *      TemporalAdjusters --- 该类通过静态方法提供了大量的常用 TemporalAdjuster 的实现
 */
public class TestTemporalAdjuster {

    public static void main(String[] args) {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        // 时间校正器
        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        // 自定义：下一个工作日
        LocalDateTime ldt5 = ldt.with((x) -> {
            LocalDateTime ldt4 = (LocalDateTime) x;

            DayOfWeek dow = ldt4.getDayOfWeek();

            if (dow.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dow.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);
    }

}
