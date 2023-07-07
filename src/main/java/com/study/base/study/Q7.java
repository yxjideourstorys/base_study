package com.study.base.study;

import java.io.*;
import java.util.*;

public class Q7 {

    public static String handle(String[] args) {
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();

        for (String str : args) {
            if (!treeMap.containsKey(str)) {
                treeMap.put(str, 1);
            } else {
                treeMap.put(str, treeMap.get(str) + 1);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(treeMap.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        StringBuffer sbr = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            sbr.append(list.get(i).getKey() + "(" + list.get(i).getValue() + ")");
            if (i < list.size() - 1) {
                sbr.append("、");
            }
        }
        return sbr.toString();
    }

    public static void main(String[] args) {
//        String text = "即信、玄讯、云通信、即信、玄讯、云通信、即信、玄讯、云通信、即信、玄讯、云通信";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("D:\\IdeaSpace\\Study\\base_study\\src\\main\\resources\\xuanwu.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("、");
                String result = handle(split);
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
