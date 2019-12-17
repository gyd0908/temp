package com.atguigu.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class saleTicket {
    public static void main(String[] args) {

        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 31; i++) ticket.sale();
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 31; i++) ticket.sale();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 31; i++) ticket.sale();
        }, "C").start();
     /*  new Thread(new Runnable() {
           @Override
           public void run() {
               for (int i = 0; i <31 ; i++) {
                   ticket.sale();
               }
           }
       },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <31 ; i++) {
                    ticket.sale();
                }
            }
        },"B").start();*/
    }

}

class Ticket {
    private Lock lock = new ReentrantLock();

    private int number = 30;

    //  synchronized void   s()
    void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t 卖出了第" + (number--) + "张票\t" + "还剩" + (number) + "张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();


        }
    }
}
