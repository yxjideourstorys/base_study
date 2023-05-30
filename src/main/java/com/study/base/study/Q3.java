package com.study.base.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 题目描述
 * 编写一个程序，将输入字符串中的字符按如下规则排序。
 * 规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
 * 如，输入： Type 输出： epTy
 * 规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
 * 如，输入： BabA 输出： aABb
 * 规则 3 ：非英文字母的其它字符保持原来的位置。
 * 如，输入： By?e 输出： Be?y
 * 注意有多组测试数据，即输入有多行，每一行单独处理（换行符隔开的表示不同行）
 * <p>
 * 输入描述:  输入字符串
 * 输出描述: 输出字符串
 * <p>
 * 输入: A Famous Saying: Much Ado About Nothing (2012/8).
 * 输出: A aaAAbc dFgghh: iimM nNn oooos Sttuuuy (2012/8).
 */
public class Q3 {

    public static void main(String[] args) throws IOException {

        String str = "A Famous Saying: Much Ado About Nothing";
        String turn = turn(str);
        System.out.println(turn);

    }

    private static String turn(String str) {
        StringBuffer sbr = new StringBuffer();
        char[] cs = str.toCharArray();
        // 第一个循环可以满足 规则1 规则2
        for (int i = 0; i < 26; i++) {
            // 获取从A-Z的所有字母  循环第一次是A，第二次是B...
            char c = (char) (i + 'A');
            for (int j = 0; j < cs.length; j++) {
                // 遍历字符数组，依次按A-Z的字母顺序比较排序。
                // A是65，26个英文字母，Z是90，Z和a之间有6个字符，所以小写a是97，z是122。所以c + 32 代表着a-z
                if (cs[j] == c || cs[j] == c + 32) {
                    sbr.append(cs[j]);
                }
            }
        }

        for (int i = 0; i < cs.length; i++) {
            if ((cs[i] >= 'a' && cs[i] <= 'z') || (cs[i] >= 'A' && cs[i] <= 'Z')) {
                continue;
            }
            sbr.insert(i, cs[i]);
        }
        return sbr.toString();
    }
}
