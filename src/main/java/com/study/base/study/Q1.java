package com.study.base.study;

public class Q1 {


//    public static void main(String[] args) {
//
//        User user = new User();
//        user.setName("木要要要");
//        reverse(user);
//        System.out.println("外：" + user.getName());
//    }
//
//    private static void reverse(User user) {
//
////        user.setName(null);
//        user = new User();
//        user.setName("东哥哥");
//        System.out.println("内：" + user.getName());
//    }

//
//    public static void main(String[] args) {
//        Integer a1 = new Integer(1);
//        Integer a2 = new Integer(1);
//
//        Integer a3 = 1;
//        Integer a4 = 1;
//
//        Integer a5 = new Integer("1");
//
//        Integer a6 = 300;
//        Integer a7 = 300;
//
//        System.out.println(a1 == a2);
//        System.out.println(a3 == a4);
//        System.out.println(a1 == a5);
//        System.out.println(a1 == a3);
//        System.out.println(a6 == a7);
//    }

    public static void main(String[] args) {
        String str1 = new String("aaa");
        String str2 = new String("aaa");

        String str3 = "aaa";
        String str4 = "aaa";

        String str5 = "aa" + new String("a");

        String str6 = "aa" + "a";

        System.out.println(str1 == str2);

        System.out.println(str3 == str4);
        System.out.println(str3 == str5);
        System.out.println(str3 == str6);

        System.out.println(str1 == str5);
        System.out.println(str1 == str6);

        System.out.println(str5 == str6);

    }



}
