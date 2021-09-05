package com.study.base.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    private static final int PERSON_NUM = 6;


    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(PERSON_NUM);

        for (int i = 1; i <= PERSON_NUM; i++) {
            new Thread(() -> {
                System.out.println("第{" + Thread.currentThread().getName() + "}个同学出门回家了");
                // 计数减1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        // 等待
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("所有人都走了，班长关门回家");
    }
}
