package com.mashibing.mycode.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Semaphore
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/11
 * @Version V1.0
 **/
public class TestSemaphore {

    public static void main(String[] args) {
        //允许一个线程同时执行
        //Semaphore s = new Semaphore(1);
        Semaphore s  = new Semaphore(2,true);

        new Thread(()->{
            try {
                s.acquire();
                System.out.println("T1..running");
                Thread.sleep(500);
                System.out.println("T1---end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                s.release();
            }

        }).start();

        new Thread(()->{
            try {
                s.acquire();
                System.out.println("T2..running");
                Thread.sleep(500);
                System.out.println("T2---end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                s.release();
            }
        }).start();
    }
}
