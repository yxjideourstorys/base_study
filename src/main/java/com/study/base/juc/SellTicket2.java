package com.study.base.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 卖票
 */
public class SellTicket2 {

    public static void main(String[] args) {

        Ticket2 ticket2 = new Ticket2();

        new Thread(()->{
            for (int i = 0; i <= 40 ; i++) {
                ticket2.ticket2();
            }
        }, "A").start();

        new Thread(()->{
            for (int i = 0; i <= 40 ; i++) {
                ticket2.ticket2();
            }
        }, "B").start();

        new Thread(()->{
            for (int i = 0; i <= 40 ; i++) {
                ticket2.ticket2();
            }
        }, "C").start();
    }
}

class Ticket2{
    int num = 30;
    private Lock lock = new ReentrantLock();

    public void ticket2(){

        lock.lock();
        try {
            if (num > 0){
                System.out.println(Thread.currentThread().getName() + "卖出第【" + num-- + "】张, 还剩【" + num + "张】");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

