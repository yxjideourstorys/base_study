package com.study.base.juc;

import java.util.concurrent.TimeUnit;

public class DeadLock {

    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) {

        new Thread(()->{
            synchronized (o1){
                System.out.println(Thread.currentThread().getName() + "持有锁o1，试图获取锁o2");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (o2){
                    System.out.println(Thread.currentThread().getName() + "获取锁o2");
                }
            }
        }, "AA").start();

        new Thread(()->{
            synchronized (o2){
                System.out.println(Thread.currentThread().getName() + "持有锁o2，试图获取锁o1");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (o1){
                    System.out.println(Thread.currentThread().getName() + "获取锁o1");
                }
            }
        }, "BB").start();
    }
}

class UnDeadLock {

    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) {

        new Thread(()->{
            synchronized (o1){
                System.out.println(Thread.currentThread().getName() + "持有锁o1，试图获取锁o2");
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (o2){
                System.out.println(Thread.currentThread().getName() + "获取锁o2");
            }
        }, "AA").start();

        new Thread(()->{
            synchronized (o2){
                System.out.println(Thread.currentThread().getName() + "持有锁o2，试图获取锁o1");
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (o1){
                System.out.println(Thread.currentThread().getName() + "获取锁o1");
            }
        }, "BB").start();
    }
}
