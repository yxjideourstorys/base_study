package com.study.base.study;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class Q5 implements Runnable {

    private static Map<String, CountDownLatch> countDownLatchMap = new HashMap<>();
    private String dependLatch;
    private String selfLatch;

    private Q5(String dependLatch, String selfLatch) {
        this.dependLatch = dependLatch;
        this.selfLatch = selfLatch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                countDownLatchMap.get(dependLatch).await();
                countDownLatchMap.put(dependLatch, new CountDownLatch(1));

                System.out.println(selfLatch);

                countDownLatchMap.get(selfLatch).countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String latchA = "A";
        String latchB = "B";
        String latchC = "C";

        countDownLatchMap.put(latchA, new CountDownLatch(1));
        countDownLatchMap.put(latchB, new CountDownLatch(1));
        countDownLatchMap.put(latchC, new CountDownLatch(1));

        Thread threadA = new Thread(new Q5(latchC, latchA));
        Thread threadB = new Thread(new Q5(latchA, latchB));
        Thread threadC = new Thread(new Q5(latchB, latchC));
        threadA.start();
        threadB.start();
        threadC.start();

        countDownLatchMap.get(latchC).countDown();
    }
}
