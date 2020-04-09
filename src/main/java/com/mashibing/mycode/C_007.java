package com.mashibing.mycode;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName C_007
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/9
 * @Version V1.0
 **/
public class C_007 {
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+",m1,start");
        try {
            TimeUnit.MILLISECONDS.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+",m1,end");
    }

    public void m2(){
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+",m2 end");
    }

    public static void main(String[] args) {
        C_007 t = new C_007();
        new Thread(t::m1,"Thread1").start();
        new Thread(t::m2,"Thread2").start();
    }
}
