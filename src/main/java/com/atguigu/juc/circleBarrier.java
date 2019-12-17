package com.atguigu.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class circleBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("收集到7颗龙珠，召唤神龙");
        });
        for (int i = 1; i <= 7; i++) {
            final int tem = i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "收集到第" + tem + "颗");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}