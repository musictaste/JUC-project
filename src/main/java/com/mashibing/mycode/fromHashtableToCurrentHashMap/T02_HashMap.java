package com.mashibing.mycode.fromHashtableToCurrentHashMap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.UUID;

/**
 * @ClassName T01_HashTable
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/15
 * @Version V1.0
 **/
public class T02_HashMap {
    static HashMap<UUID,UUID> map = new HashMap<>();

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

        for(Thread t:threads){
            t.start();
        }

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
    }
}
