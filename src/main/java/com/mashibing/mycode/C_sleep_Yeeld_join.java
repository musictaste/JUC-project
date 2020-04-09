package com.mashibing.mycode;

import javax.sound.midi.Soundbank;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName C_sleep_Yeeld_join
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/9
 * @Version V1.0
 **/
public class C_sleep_Yeeld_join {
    public static void testSleep(){
        new Thread(()->{
            System.out.println("sleep start");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sleep end");
        }).start();
    }

    public static void testYeald(){
        new Thread(()->{
           for(int i=0;i<100;i++){
               System.out.println("A"+i);
               if(i%10 == 0){
                   Thread.yield();
               }
           }
        }).start();

        new Thread(()->{
            for(int i=0;i<100;i++){
                System.out.println("=============B"+i);
                if(i%10 ==0) Thread.yield();
            }
        }).start();

    }

    static void testJoin(){
        Thread t1 = new Thread(()->{
           for(int i=0;i<10;i++){
               System.out.println(i);
               try {
                   TimeUnit.MILLISECONDS.sleep(500);
//                   Thread.sleep(500);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        Thread t2 = new Thread(()->{
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for(int i=0;i<10;i++){
                System.out.println("=======B"+i);
           }
        });

        t1.start();
        t2.start();
    }

    static void testJoin2(){
        Thread t1 = new Thread(()->{
           for (int i=0;i<10;i++){
               System.out.println("T1"+i);
               try {
                   TimeUnit.MILLISECONDS.sleep(500);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        Thread t2 = new Thread(()->{
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i=0;i<10;i++){
                System.out.println("-----------T2"+i);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(()->{
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i=0;i<10;i++){
                System.out.println("===========T3"+i);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });



        t1.start();
        t2.start();
        t3.start();

    }

    public static void main(String[] args) {
//        testSleep();
//        testYeald();
//        testJoin();
        testJoin2();
    }
}
