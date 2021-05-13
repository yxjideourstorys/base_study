package com.study.base.data_structure.queue;

import java.util.Scanner;

/**
 * 循环队列
 * 使用牺牲一个存储空间的方式
 */
public class CircleArrayQueue {

    /**
     * 队头指针
     */
    private int front;

    /**
     * 队尾指针
     */
    private int rear;

    /**
     * 队列最大容量
     */
    private int maxSize;

    /**
     * 队列对象
     */
    private int[] arrQueue;

    public CircleArrayQueue() {
    }

    public CircleArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        arrQueue = new int[maxSize];
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 判断队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return front == (rear + 1) % maxSize;
    }

    /**
     * 向队列中添加元素
     *
     * @param value
     */
    public void addQueue(int value) {
        //添加元素之前先判断队列是否已满
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        //front 指针指向的是队首元素
        //rear 指针指向的是队尾元素的下一个位置
        //添加元素的步骤为：
        // #1 在rear 位置处添加元素
        arrQueue[rear] = value;
        // #2 将rear 指针向下移动一个位置
        rear = (rear + 1) % maxSize;
    }

    /**
     * 出队列操作
     *
     * @return
     */
    public int outQueue() {
        //出队列之前先判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("当前队列为空");
        }
        //出队列的思路：先将front处的元素出队列，然后将front指针向下移一个位置
        // #1 使用一个临时变量记录front处，将要出队列的元素值
        int val = arrQueue[front];
        // #2 将front指针向下移一个位置
        front = (front + 1) % maxSize;
        // #3 将记录的临时变量返回
        return val;
    }

    /**
     * 显示队列所有数据
     */
    public void allQueue() {
        //判断队列元素是否为空
        if (isEmpty()) {
            throw new RuntimeException("当前队列为空");
        }
        //思路： 从front处开始遍历，遍历有效数据长度(rear + maxSize - front) % maxSize。因位置是从front开始的，帮为front + (rear + maxSize - front) % maxSize
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arrQueue[%d]：%d\n", i % maxSize, arrQueue[i % maxSize]);
        }
    }

    /**
     * 当前队列有效数据的个数
     *
     * @return
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 查询队列的头数据
     *
     * @return
     */
    public int frontQueue() {
        if (isEmpty()) {
            throw new RuntimeException("当前队列为空");
        }
        return arrQueue[front % maxSize];
    }


    public static void main(String[] args) {
        //创建一个队列，并指定大小
        CircleArrayQueue arrayQueue = new CircleArrayQueue(10);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("a，向队列中添加数据");
            System.out.println("b，数据出队列");
            System.out.println("c，查询队列中的所有数据");
            System.out.println("d, 查询队列的头数据");
            try {
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
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }


}
