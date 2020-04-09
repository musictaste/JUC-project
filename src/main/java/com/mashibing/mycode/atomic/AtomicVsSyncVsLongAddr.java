package com.mashibing.mycode.atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @ClassName AtomicVsSyncVsLongAddr
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/10
 * @Version V1.0
 **/
public class AtomicVsSyncVsLongAddr {
    private static long count1 = 0;
    private static AtomicLong count2 = new AtomicLong(0);
    private static LongAdder count3 = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads  =  new Thread[1000];

        Object lock = new Object();
        for(int i =0;i<threads.length;i++){
            threads[i]=new Thread(()->{
                for(int j=0;j<100000;j++){
                    synchronized (lock){
                        count1++;
                    }
                }
            });
        }
        Long start = System.currentTimeMillis();
        for(Thread t:threads) t.start();
        for(Thread t:threads) t.join();
        Long end = System.currentTimeMillis();
        System.out.println("Synchronized  "+count1+" time:"+(end-start));


        for(int i=0;i<threads.length;i++){
            threads[i]= new Thread(()->{
                for(int j=0;j<100000;j++){
                    count2.incrementAndGet();
                }
            });
        }
        start = System.currentTimeMillis();
        for(Thread t:threads) t.start();
        for(Thread t:threads) t.join();
        end = System.currentTimeMillis();
        System.out.println("Atomic  "+count2+" time:"+(end-start));


        for(int i=0;i<threads.length;i++){
            threads[i]= new Thread(()->{
                for(int j=0;j<100000;j++){
                    count3.increment();
                }
            });
        }
        start = System.currentTimeMillis();
        for(Thread t:threads) t.start();
        for(Thread t:threads) t.join();
        end = System.currentTimeMillis();
        System.out.println("LongAdder  "+count3+" time:"+(end-start));
    }
}
