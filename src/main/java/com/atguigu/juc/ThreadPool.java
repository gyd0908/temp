package com.atguigu.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//线程池
public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        Ticket ticket=new Ticket();
            try {
                service.execute(() -> {for (int i = 1; i <=30; i++) ticket.sale();});
            }  finally {
                service.shutdown();
            }
    }
}
