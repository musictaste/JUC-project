package com.mashibing.mycode.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName R_005
 * @Description: TODO
 * @Author ¿ÓÌµ
 * @Date 2020/3/10
 * @Version V1.0
 **/
public class R_005 {
    Lock lock = new ReentrantLock();

    public void m(){
        for(int i=0;i<100;i++){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+" ªÒµ√À¯");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        R_005 t = new R_005();
        new Thread(t::m,"t1").start();
        new Thread(t::m,"t2").start();
    }
}
