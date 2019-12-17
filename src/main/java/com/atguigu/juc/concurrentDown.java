package com.atguigu.juc;

import java.util.concurrent.CountDownLatch;

public class concurrentDown {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(6);
       //有6名同学
        for (int i = 1; i <=6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "班长锁门");
    }
}
