package com.study.base.juc;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类
 */
@Slf4j
class Resource {

    // 标识位
    private int flag = 1;

    // 创建锁
    private Lock lock = new ReentrantLock();

    // 创建3个线程
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    // 打印5次
    public void print5(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 1){
                c1.await();
            }

            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "打印第 "+ i +" 次，第 "+ loop + " 轮");
            }

            // 标识符设置为2
            flag = 2;
            // 通知线程c2
            c2.signal();
        }finally {
            lock.unlock();
        }
    }

    // 打印10次
    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 2){
                c2.await();
            }

            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "打印第 "+ i +" 次，第 "+ loop + " 轮");
            }

            // 标识符设置为3
            flag = 3;
            // 通知线程c3
            c3.signal();
        }finally {
            lock.unlock();
        }
    }

    // 打印15次
    public void print15(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 3){
                c3.await();
            }

            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "打印第 "+ i +" 次，第 "+ loop + " 轮");
            }

            // 标识符设置为3
            flag = 1;
            // 通知线程c3
            c1.signal();
        }finally {
            lock.unlock();
        }
    }
}


public class CustomizedCommunicatiion {

    public static void main(String[] args) {
        Resource resource = new Resource();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    resource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    resource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    resource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();
    }
}