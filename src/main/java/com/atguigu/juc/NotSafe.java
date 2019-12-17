package com.atguigu.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class NotSafe {
    public static void main(String[] args) {
        //1.new Collections.synchronizedList()
        // 2. new  Vector()
        //3.new CopyOnWriteArrayList()
        List<String> list = new CopyOnWriteArrayList();//new ArrayList<>();
        Set<String> set = new CopyOnWriteArraySet<>();
        Map<String ,String>map=new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 6));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 6));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
