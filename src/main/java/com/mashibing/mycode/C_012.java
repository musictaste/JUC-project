package com.mashibing.mycode;

import org.checkerframework.checker.units.qual.C;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName C_012
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/10
 * @Version V1.0
 **/
public class C_012 {
    private volatile boolean running =true;

    public void m(){
        System.out.println(Thread.currentThread().getName()+" start");
        while (running){

        }
        System.out.println(Thread.currentThread().getName()+" end");
    }

    public static void main(String[] args) {
        C_012 t = new C_012();
        new Thread(t::m).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running=false;

    }
}
