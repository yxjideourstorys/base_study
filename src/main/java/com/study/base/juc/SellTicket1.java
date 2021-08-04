package com.study.base.juc;

/**
 * 卖票
 */
public class SellTicket1 {

    public static void main(String[] args) {

        Ticket1 ticket1 = new Ticket1();

        new Thread(()->{
            for (int i = 0; i <= 40 ; i++) {
                ticket1.ticket1();
            }
        }, "A").start();

        new Thread(()->{
            for (int i = 0; i <= 40 ; i++) {
                ticket1.ticket1();
            }
        }, "B").start();

        new Thread(()->{
            for (int i = 0; i <= 40 ; i++) {
                ticket1.ticket1();
            }
        }, "C").start();
    }
}

class Ticket1{
    int num = 30;

    public synchronized void ticket1(){

        if (num > 0){
            System.out.println(Thread.currentThread().getName() + "卖出第【" + num-- + "】张, 还剩【" + num + "张】");
        }
    }
}
