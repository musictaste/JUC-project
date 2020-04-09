package com.mashibing.mycode.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName R_003
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/10
 * @Version V1.0
 **/
public class R_003 {
    Lock lock = new ReentrantLock();

    void m1(){
        System.out.println("m1 start");
        try {
            lock.lock();
            for(int i=0;i<10;i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        System.out.println("m1 end");
    }

    void m2(){
        boolean locked  = false;
        try {
            locked = lock.tryLock(5,TimeUnit.SECONDS);
            System.out.println("m2......"+locked);
        } catch (InterruptedException e) {
            System.out.println("");
        } finally {
            if(locked) {
                System.out.println("m2 locked=true");
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        R_003 t = new R_003();
        new Thread(t::m1).start();
        new Thread(t::m2).start();
    }
}
