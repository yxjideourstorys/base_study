package com.study.base.java8.date_new;

import java.time.*;

/**
 *     一、 LocalDate、LocalTime、LocalDateTime  三个的用法相同
 *
 *     二、Instant：时间戳(以Unix元年，1970年1月1日，00：00：00到某个时间之间的毫秒值)
 *
 *     三、Duration：计算2个“时间”之间的间隔
 *         Period：计算2个“日期”之间的间隔
 */
public class TestLocalDateTime {

    /**
     * 一、 LocalDate、LocalTime、LocalDateTime  三个的用法相同
     */
    public void testLDT(){
        // 获取当前时间
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        // 自定义时间，返回LocalDateTime
        LocalDateTime ldt2 = LocalDateTime.of(2011, 11, 11, 11, 11, 11);
        System.out.println(ldt2);

        // 加2年
        LocalDateTime ldt3 = ldt.plusYears(2);
        System.out.println(ldt3);

        // 减2年
        LocalDateTime ldt4 = ldt.minusMonths(2);
        System.out.println(ldt4);

        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonthValue());
        System.out.println(ldt.getDayOfMonth());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getMinute());
        System.out.println(ldt.getSecond());
    }

    /**
     * Instant：时间戳(以Unix元年，1970年1月1日，00：00：00到某个时间之间的毫秒值)
     */
    public void testInstant(){
        // 默认获取UTC 时区
        Instant ins1 = Instant.now();
        System.out.println(ins1);

        // 计算时间偏移量
        OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);

        // 毫秒值/时间戳
        System.out.println(ins1.toEpochMilli());

        Instant ins2 = Instant.ofEpochMilli(60);
        System.out.println(ins2);
    }

    /**
     *  三、Duration：计算2个“时间”之间的间隔
     *     Period：计算2个“日期”之间的间隔
     */
    public void testD(){
        Instant ins1 = Instant.now();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant ins2 = Instant.now();

        // Duration：计算2个“时间”之间的间隔
        Duration duration = Duration.between(ins1, ins2);
        System.out.println(duration.getSeconds());
        System.out.println(duration.toMillis());

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        LocalTime lt1 = LocalTime.now();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LocalTime lt2 = LocalTime.now();

        Duration duration1 = Duration.between(lt1, lt2);
        System.out.println(duration1.getSeconds());
        System.out.println(duration1.toMillis());
    }

    public void testP(){
        LocalDate ld1 = LocalDate.of(2015,1,1);
        LocalDate ld2 = LocalDate.now();

        Period period = Period.between(ld1, ld2);
        System.out.println(period);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }
}
