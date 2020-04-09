package com.mashibing.mycode.interview_01;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName T01_withVolatile
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/12
 * @Version V1.0
 **/
public class T07_lockSupport {
    /*volatile */List<Object> list = new LinkedList<>();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T07_lockSupport o = new T07_lockSupport();

        Thread t2 = new Thread(()->{
            System.out.println("T2 start");
            if(o.size() !=5){
                LockSupport.park();
            }
            System.out.println("T2...end");
        });
        t2.start();

        new Thread(()->{
            System.out.println("T1 start");
            for (int i = 0; i < 10; i++) {
                o.add(new Object());
                System.out.println("add  "+i);
                if(o.size()==5){
                    LockSupport.unpark(t2);
                }
                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        },"T1").start();

    }
}
