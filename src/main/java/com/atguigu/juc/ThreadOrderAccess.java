package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadOrderAccess {
    public static void main(String[] args) {
        Order order = new Order();
        for (int i = 0; i <10 ; i++) {
             new Thread(() -> {
                       order.Print5();
                     }, "A").start();
        }
        for (int i = 0; i <10 ; i++) {
            new Thread(() -> {
                order.Print10();
            }, "B").start();
        }
        for (int i = 0; i <10 ; i++) {
            new Thread(() -> {
                order.Print15();
            }, "C").start();
        }
    }
}

class Order {
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    private int flag = 1;

    public void Print5() {
        lock.lock();
        try {
            while (flag != 1) {
                c1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
                flag = 2;
                c2.signal();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void Print10() {
        lock.lock();
        try {
            while (flag != 2) {
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
                flag = 3;
                c3.signal();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void Print15() {
        lock.lock();
        try {
            while (flag != 3) {
                c3.await();
            }
            for (int i = 0; i < 15; i++) {

                System.out.println(Thread.currentThread().getName() + "\t" + i);
                flag = 1;
                c1.signal();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
