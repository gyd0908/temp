package com.atguigu.juc;

import java.util.concurrent.TimeUnit;

public class lock8 {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
       Thread.sleep(100);
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}

class Phone {
    public   static synchronized void sendEmail()throws  Exception {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendEmail()");
    }

    public  synchronized void sendSMS() throws Exception{
      //  TimeUnit.SECONDS.sleep(3);
        System.out.println("sendSMS()");
    }
    public  void  Hello(){
         System.out.println("Hello");
    }
}