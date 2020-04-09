package com.mashibing.mycode;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName HowToCreateThread
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/9
 * @Version V1.0
 **/
public class C_001 {
     private static class T extends Thread{
        @Override
        public void run() {
            System.out.println("T start");
            for(int i=0;i<10;i++){
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T content");
            }
            System.out.println("T end");
        }
    }

    public static void main(String[] args) {
        T t = new T();
//        t.run();
        t.start();
        for(int i=0;i<10;i++){
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }

}
