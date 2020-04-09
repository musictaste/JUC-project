package com.mashibing.mycode.interview_01;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName T01_withVolatile
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/12
 * @Version V1.0
 **/
public class T06_countdownLatch {
    /*volatile */List<Object> list = new LinkedList<>();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T06_countdownLatch o = new T06_countdownLatch();
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(()->{
            System.out.println("T2 start");
            if(o.size() !=5){
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("T2...end");
            latch.countDown();
        }).start();

        new Thread(()->{
            System.out.println("T1 start");
            for (int i = 0; i < 10; i++) {
                o.add(new Object());
                System.out.println("add  "+i);
                if(o.size()==5){
                    // 打开门闩，让t2得以执行
                    latch.countDown();
                    try {
                        latch.await();
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
        },"T1").start();

    }
}
