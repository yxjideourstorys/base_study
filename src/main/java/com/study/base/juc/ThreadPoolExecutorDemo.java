package com.study.base.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {

//        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
//        ExecutorService threadPool2 = Executors.newCachedThreadPool();
        ExecutorService threadPool3 = Executors.newSingleThreadExecutor();

        // 10个人
        try {
            for (int i = 1; i <= 10; i++) {
                int num = i;
                threadPool3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "第" + num + "个人");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool3.shutdown();
        }
    }
}
