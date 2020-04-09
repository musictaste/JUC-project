package com.mashibing.mycode.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName R_002
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/10
 * @Version V1.0
 **/
public class R_002 {
    ReentrantLock lock = new ReentrantLock();
    void m1(){
        try {
            lock.lock();
            for(int i=0;i<10;i++){
               TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2(){
        try {
            lock.lock();
            System.out.println("m2........");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        R_002 t = new R_002();
        new Thread(t::m1).start();
        new Thread(t::m2).start();
    }
}
