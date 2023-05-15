package com.study.base.study;

public class Q2 {

    public static void main(String[] args) {
        // https://blog.csdn.net/pengshuhui123/article/details/105774898
        Integer i89 = Integer.valueOf(89);
        Integer i89_1 = 89;
        Integer i89_2 = new Integer(89);
        System.out.println(i89 == i89_1);
        System.out.println(i89 == i89_2);
        System.out.println(i89 == 89);
    }


}
