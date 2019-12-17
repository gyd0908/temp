package com.atguigu.juc;


import java.util.concurrent.*;

public class MyThreadPool {
    public static void main(String[] args) {

    ExecutorService service = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
               new ArrayBlockingQueue<Runnable>(3),
               Executors.defaultThreadFactory(),
              new ThreadPoolExecutor.AbortPolicy()
           //  new ThreadPoolExecutor.CallerRunsPolicy()
          //  new ThreadPoolExecutor.DiscardOldestPolicy()
            //new ThreadPoolExecutor.DiscardPolicy()
         );


            try {
        for (int i = 1; i <=8; i++) {
                service.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
            }  finally {
                service.shutdown();
            }
        }
    }

