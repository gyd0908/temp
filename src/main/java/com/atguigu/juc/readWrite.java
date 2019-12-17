package com.atguigu.juc;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class readWrite {
    public static void main(String[] args) {
        Demo demo = new Demo();
        for (int i = 0; i < 5; i++) {
            final int tem = i;
            new Thread(() -> {
                demo.put(tem + "", tem + "");
            }, String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int tem = i;
            new Thread(() -> {
                demo.get(tem + "");
            }, String.valueOf(i)).start();
        }
    }
}

class Demo {
    private volatile Map<String, String> map = new HashMap<>();
    ReentrantReadWriteLock rw = new ReentrantReadWriteLock();

    void put(String key, String value) {
        rw.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写开始");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写结束");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rw.writeLock().unlock();
        }
    }

    void get(String key) {
        rw.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读开始");
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "读结束");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rw.readLock().unlock();
        }
    }
}