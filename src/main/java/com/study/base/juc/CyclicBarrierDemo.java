package com.study.base.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    private static final int NUM = 7;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUM, () -> {
            System.out.println("集齐" + NUM + "颗龙珠了");
        });


        for (int i = 1; i <= NUM; i++) {
            new Thread(() -> {
                try {
                    System.out.println("集齐第" + Thread.currentThread().getName() + "颗龙珠了");
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
