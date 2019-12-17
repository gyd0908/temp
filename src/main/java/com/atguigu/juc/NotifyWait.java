package com.atguigu.juc;

class Apple {
    private int number = 0;

    public synchronized    void sale() throws Exception {
        while (number != 0) {
            this.wait();
        }
        ++number;
        System.out.println(Thread.currentThread().getName()+"##sale");
        this.notifyAll();
    }

    public synchronized void sale1() throws Exception {
        while (number == 0) {
            this.wait();
        }
        --number;
        System.out.println(Thread.currentThread().getName()+"##sale1");
        this.notifyAll();
    }
}

public class NotifyWait {
    public static void main(String[] args) {
        Apple apple = new Apple();

      new Thread(() ->
      {for (int i = 0; i < 10; i++) {
          try {
              apple.sale();
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
      },"A").start();
        new Thread(() ->
        {for (int i = 0; i < 10; i++) {
            try {
                apple.sale1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        },"B").start();
    }
}
