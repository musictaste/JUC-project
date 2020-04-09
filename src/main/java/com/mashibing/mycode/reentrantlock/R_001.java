package com.mashibing.mycode.reentrantlock;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName R_001
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/10
 * @Version V1.0
 **/
public class R_001 {
    synchronized void m1(){
        System.out.println("m1 start");
        for(int i=0;i<10;i++){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(i==2){
                m2();
            }
        }
        System.out.println("m1 end");
    }

    synchronized void m2(){
        System.out.println("m2 .....");
    }

    public static void main(String[] args) {
        R_001 t = new R_001();
        new Thread(()->{
            t.m1();
        },"T1").start();

//        new Thread(t::m2,"T2").start();
    }
}
