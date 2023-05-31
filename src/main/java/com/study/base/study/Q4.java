package com.study.base.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Q4 {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("123abccd");
        list.add("abcdc123");
        list.add("1234abc");
        list.add("ab1234cd");

        List<String> result = dedup(list);
        for (String s: result) {
            System.out.println("结果：" + s);
        }


    }

    private static List<String> dedup(List<String> list) {
        String num = "[0-9]";
        HashMap<String, String> map = new HashMap<>();
        for (String str: list) {
            char[] charStr = str.toCharArray();
            StringBuffer sbr = new StringBuffer();
            for (char c: charStr) {
                if (String.valueOf(c).matches(num)){
                    sbr.append(c);
                }
            }
            map.put(sbr.toString(), str);
        }

        List<String> result = new ArrayList<>();
        map.forEach((key, value) -> {
            result.add(value);
        });
        return result;
    }


}
