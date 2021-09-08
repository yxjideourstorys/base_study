package com.study.base.juc;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadWriteResource{

    private volatile Map<String, Object> map = new HashMap<>();

    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value){
        rwLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + " 开始写数据了，" + key);
            map.put(key, value);

            TimeUnit.SECONDS.sleep(2);

            System.out.println(Thread.currentThread().getName() + " 写完了，" + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public Object get(String key){
        rwLock.readLock().lock();

        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + " 开始读数据了，" + key);
            result = map.get(key);

            TimeUnit.SECONDS.sleep(2);

            System.out.println(Thread.currentThread().getName() + " 读完了，" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }

        return result;
    }
}


public class ReadWriteLockDemo {

    public static void main(String[] args) {

        ReadWriteResource rwResource = new ReadWriteResource();

        for (int i = 1; i <= 5; i++) {
            int num = i;
            new Thread(()->{
                rwResource.put(num+"", num);
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            int num = i;
            new Thread(()->{
                rwResource.get(num+"");
            }, String.valueOf(i)).start();
        }


    }

}
