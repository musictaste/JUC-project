package com.mashibing.mycode.fromHashtableToCurrentHashMap;

import java.util.Hashtable;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName T01_HashTable
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/15
 * @Version V1.0
 **/
public class T04_ConcurrentHashMap {
    static ConcurrentHashMap<UUID,UUID> map = new ConcurrentHashMap<>();

    static int count = Constants.COUNT;
    static final int THREAD_COUNT = Constants.THREAD_COUNT;

    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];

    static {
        for (int i = 0; i < count; i++) {
            keys[i]=UUID.randomUUID();
            values[i]=UUID.randomUUID();
        }
    }

    static class MyThread extends Thread{
        int start;
        int size = count/THREAD_COUNT;

        public MyThread(int start){
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start+size; i++) {
                map.put(keys[i],values[i]);
            }
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(i*(count/THREAD_COUNT));
        }

        for(Thread t:threads) t.start();

        for (Thread t:threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime  = System.currentTimeMillis();
        System.out.println(endTime-startTime);

        System.out.println(map.size());

        //=========================
        startTime = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 1000000; j++) {
                    map.get(keys[10]);
                }
            });
        }

        for(Thread t:threads) t.start();

        for(Thread t:threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}
