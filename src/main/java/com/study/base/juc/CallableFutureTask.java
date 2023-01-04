package com.study.base.juc;

import java.util.concurrent.FutureTask;

public class CallableFutureTask {

    public static void main(String[] args) {

        FutureTask<String> futureTask = new FutureTask<>(()->{
            System.out.println("callable执行");
            return "success";
        });

        new Thread(futureTask).start();

        try {
            System.out.println(futureTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
