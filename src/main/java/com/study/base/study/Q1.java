package com.study.base.study;

public class Q1 {


    public static void main(String[] args) {

        User user = new User();
        user.setName("木要要要");
        reverse(user);
        System.out.println("外：" + user.getName());
    }

    private static void reverse(User user) {

//        user.setName(null);
        user = new User();
        user.setName("东哥哥");
        System.out.println("内：" + user.getName());
    }


}
