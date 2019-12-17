package com.atguigu.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        //信息量
        //permit为1，相当于synchronized同步锁
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i<=6; i++) {
            new Thread(() -> {
                boolean flag = false;
                try {
                   semaphore.acquire();
                    flag = true;
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (flag) {
                        semaphore.release();
                    }
                }

            }, String.valueOf(i)).start();
        }
    }
}
