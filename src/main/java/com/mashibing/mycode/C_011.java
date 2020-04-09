package com.mashibing.mycode;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName C_011
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/9
 * @Version V1.0
 **/
public class C_011 {
    private int count =0;
    synchronized void m(){
        while (true){
            count++;
            System.out.println(Thread.currentThread().getName()+",count:"+count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count ==5){
                int i =1/0;
                System.out.println(i);
            }
        }

    }

    public static void main(String[] args) {
        C_011 t = new C_011();
        Runnable r= new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };

        new Thread(r,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r,"t2").start();
    }
}
