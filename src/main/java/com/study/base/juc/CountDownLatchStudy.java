package com.study.base.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchStudy {

    public static void main(String[] args) {

        int personNum = 6;

        CountDownLatch countDownLatch = new CountDownLatch(personNum);

        for (int i = 1; i <= personNum; i++) {
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
