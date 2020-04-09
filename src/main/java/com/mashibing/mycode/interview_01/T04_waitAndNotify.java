package com.mashibing.mycode.interview_01;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T01_withVolatile
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/12
 * @Version V1.0
 **/
public class T04_waitAndNotify {
    /*volatile*/ List<Object> list = new LinkedList<>();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T04_waitAndNotify o = new T04_waitAndNotify();

        final Object lock = new Object();

        new Thread(()->{
            synchronized (lock){
                System.out.println("T2 start");
                if(o.size() !=5){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("T2...end");
                //通知t1继续执行
                lock.notify();
            }
        }).start();

        new Thread(()->{
            System.out.println("T1 start");
            synchronized (lock){
                for (int i = 0; i < 10; i++) {
                    o.add(new Object());
                    System.out.println("add  "+i);
                    if(o.size()==5){
                        //释放锁，让T2执行
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    /*try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
            }
        },"T1").start();

    }
}
