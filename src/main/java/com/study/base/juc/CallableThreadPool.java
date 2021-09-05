package com.study.base.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableThreadPool {

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();

        Future<String> future = exec.submit(() -> {
            System.out.println("callable执行成功");
            return "success";
        });

        try {
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        exec.shutdown();
    }
}
