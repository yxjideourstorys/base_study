package com.study.base.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 6辆车抢3个车位
 */
public class SemaphoreDemo {

    /**
     * 车位数
     */
    private static final int ACQUIRE_NUM = 3;
    /**
     * 车辆数
     */
    private static final int CAR_NUM = 6;

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(ACQUIRE_NUM);

        for (int i = 1; i <= CAR_NUM; i++) {
            new Thread(() -> {
                try {
                    // 抢车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 抢到了车位");

                    // 不定时停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    System.out.println(Thread.currentThread().getName()+" 车离开了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
