package com.mashibing.mycode.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestCountDownLatch
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/11
 * @Version V1.0
 **/
public class TestCountDownLatch {
    public static void main(String[] args) {
        useCountDownLatch();
//        useJoin();
    }


    public static void useCountDownLatch(){
        Thread[] threads = new Thread[10];
        CountDownLatch latch = new CountDownLatch(5);//5

        for(int i=0;i<threads.length;i++){
            threads[i]=new Thread(()->{
                int result =0;
                for(int j=0;j<100000;j++)  result++;
                System.out.println(Thread.currentThread().getName()+" result:"+result);
                latch.countDown();
            });
        }

        Long start = System.currentTimeMillis();
        for(Thread t: threads) t.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis();
        System.out.println("花费的时间："+(end-start));

        System.out.println("latch end");
    }

    public static void useJoin(){
        Thread[] threads = new Thread[10];
        for(int i=0;i<threads.length;i++){
            threads[i]=new Thread(()->{
                int result =0;
                for(int j=0;j<100000;j++)  result++;
                System.out.println(Thread.currentThread().getName()+" result:"+result);
            });
        }

        Long start = System.currentTimeMillis();
        for(Thread t: threads) t.start();

        for(int i = 0; i < 10; i++){
            try {
                threads[i].join();
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        Long end = System.currentTimeMillis();
        System.out.println("花费的时间："+(end-start));
        System.out.println("join end");
    }
}
