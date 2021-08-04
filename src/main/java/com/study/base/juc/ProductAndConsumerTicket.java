package com.study.base.juc;

/**
 * 线程之间通信判断必须要使用while来进行循环判断
 */
public class ProductAndConsumerTicket {

    public static void main(String[] args) {
        Ticket3 ticket3 = new Ticket3();

        new Thread(() ->{
            for (int i = 0; i < 40; i++) {
                try {
                    ticket3.addTicket();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() ->{
            for (int i = 0; i < 40; i++) {
                try {
                    ticket3.addTicket();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() ->{
            for (int i = 0; i < 40; i++) {
                try {
                    ticket3.subTicket();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() ->{
            for (int i = 0; i < 40; i++) {
                try {
                    ticket3.subTicket();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }

}

class Ticket3{
    int num = 0;

    public synchronized void addTicket() throws InterruptedException {
        while (num != 0){
            this.wait();
        }

        num++;
        System.out.println(Thread.currentThread().getName() + "\t" + num);

        this.notifyAll();
    }

    public synchronized void subTicket() throws InterruptedException {
        while (num == 0){
            this.wait();
        }

        num--;
        System.out.println(Thread.currentThread().getName() + "\t" + num);

        this.notifyAll();
    }
}
