package com.atguigu.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallAble {
    public static void main(String[] args) throws Exception {
        FutureTask futureTask = new FutureTask<>(new Mythread());
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "aaaaa");
        }, "A").start();
        System.out.println(futureTask.get());

    }
}

class Mythread implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        return 1;
    }
}