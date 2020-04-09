package com.mashibing.mycode.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName AtomicInteger
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/10
 * @Version V1.0
 **/
public class AtomicIntegerTest {
    /*private volatile int count =0;

    public synchronized void m (){
        for(int i=0;i<10000;i++){
            count++;
        }
    }*/

    AtomicInteger count = new AtomicInteger(0);
    public void m(){
        for(int i =0;i<10000;i++){
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        AtomicIntegerTest t = new AtomicIntegerTest();
        List<Thread> threadList = new ArrayList<>();
        for(int i=0;i<10;i++){
            threadList.add(new Thread(t::m));
        }
        threadList.forEach((o)->o.start());

        threadList.forEach((o)-> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}
