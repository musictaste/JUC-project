package com.mashibing.mycode.map;

import org.checkerframework.checker.units.qual.K;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName TestMap
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/15
 * @Version V1.0
 **/
public class TestMap {
    static final int THREAD_COUNT =100;
    static final int COUNT =10000;
//    static Map<String,String> map = new Hashtable<>(COUNT*THREAD_COUNT);
//    static Map<String,String> map = new HashMap<>(COUNT*THREAD_COUNT);
//    static Map<String,String> map = Collections.synchronizedMap( new HashMap<String, String>(COUNT*THREAD_COUNT));
    static Map<String,String> map = new ConcurrentHashMap<>(COUNT*THREAD_COUNT);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        for (int i = 0; i < threads.length; i++) {
            int finalI = i;
            threads[i]=new Thread(()->{
                for (int j = 0; j < COUNT; j++) {
                    map.put("a-"+ finalI +"-"+j,"b"+ finalI +"-"+j);
                }
                latch.countDown();
            });
        }

        Arrays.asList(threads).forEach(t->t.start());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        System.out.println(endTime-startTime);
        System.out.println(map.size());
    }
}
