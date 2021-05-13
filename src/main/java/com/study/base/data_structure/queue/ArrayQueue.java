package com.study.base.data_structure.queue;

import java.util.Scanner;

/**
 * 数组实现队列
 *    存在假溢出问题
 *      解决方式：循环队列
 */
public class ArrayQueue {

    /**
     * 队列的最大容量
     */
    private int maxSize;

    /**
     * 队列的头指针
     */
    private int front;

    /**
     * 队列的尾指针
     */
    private int rear;

    /**
     * 队列对象
     */
    private int[] arrQueue;

    public ArrayQueue() {
    }

    /**
     * 初始化构造器
     *
     * @param arrMaxSize
     */
    public ArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        arrQueue = new int[maxSize];
        this.front = -1;
        this.rear = -1;
    }

    /**
     * 队列的判空
     *
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 队列的判满
     *
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 队列添加数据
     */
    public void addQueue(int value) {
        //判断队列是否已满
        if (isFull()) {
            throw new RuntimeException("队列已满，无法添加数据");
        }
        rear++;
        arrQueue[rear] = value;
    }

    /**
     * 数据出队列
     */
    public int outQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        front++;
        return arrQueue[front];
    }

    /**
     * 查询队列数据
     */
    public void allQueue() {
        //判空
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }

        for (int i = 0; i < maxSize; i++) {
            System.out.printf("arrQueue[%d]：%d\n", i, arrQueue[i]);
        }
    }

    /**
     * 查询队列头数据
     */
    public int frontQueue() {
        if (isEmpty()) {
            throw new RuntimeException("列表为空");
        }

        return arrQueue[front + 1];
    }


    public static void main(String[] args) {
        //创建一个队列，并指定大小
        ArrayQueue arrayQueue = new ArrayQueue(10);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("a，向队列中添加数据");
            System.out.println("b，数据出队列");
            System.out.println("c，查询队列中的所有数据");
            System.out.println("d, 查询队列的头数据");
            switch (scanner.next()) {
                case "a":
                    System.out.println("请输入一个数字");
                    arrayQueue.addQueue(scanner.nextInt());
                    break;
                case "b":
                    System.out.println("数据出队列操作");
                    int res1 = arrayQueue.outQueue();
                    System.out.println("出队列数据为：" + res1);
                    break;
                case "c":
                    System.out.println("查询队列中的所有数据");
                    arrayQueue.allQueue();
                    break;
                case "d":
                    System.out.println("查询队列的头数据");
                    int res2 = arrayQueue.frontQueue();
                    System.out.println("队列头数据为：" + res2);
                    break;
                default:
                    System.out.println("无匹配操作");
            }
        }
    }
}
