package com.study.base.java8.date_new;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  DateTimeFormatter 格式化时间/日期
 */
public class TestDateTimeFormat {

    public static void main(String[] args) {
        // 随便拿一个试试
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();

        String strDate = ldt.format(dtf);
        String strDate1 = dtf.format(ldt);
        System.out.println(strDate);
        System.out.println(strDate1);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // 自定义时间日期格式
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String strDate2 = dtf2.format(ldt);
        System.out.println(strDate2);

        // 转换回以前的格式
        LocalDateTime newDate = LocalDateTime.parse(strDate2, dtf2);
        System.out.println(newDate);
    }

}
