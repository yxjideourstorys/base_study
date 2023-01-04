package com.study.base.juc;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableThreadPoolMore {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();

        for (int i = 0; i < 5; i++) {
            int id = i;
            results.add(exec.submit(()->{
                for(int j = 0; j < 5; j++){
                    System.out.println("Thread"+ id);
                    Thread.sleep(1000);
                }
                return "Result of callable: " + id;
            }));
        }

        for (Future<String> fs : results) {
            if (fs.isDone()) {
                try {
                    System.out.println(fs.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("MyCallableTask任务未完成！");
            }
        }
        exec.shutdown();
    }
}
