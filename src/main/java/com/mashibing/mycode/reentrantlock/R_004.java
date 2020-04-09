package com.mashibing.mycode.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName R_004
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/10
 * @Version V1.0
 **/
public class R_004 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(()->{
            try {
                lock.lock();
                System.out.println("m1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("m1 end");
            } catch (InterruptedException e) {
                System.out.println("m1 interrupted");
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(()->{
            try {
                lock.lockInterruptibly();
                System.out.println("m2 start");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("m2 end");
            } catch (InterruptedException e) {
                System.out.println("m2 interrupted");
            } finally {
                lock.unlock();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();
    }
}
