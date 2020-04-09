package com.mashibing.mycode.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName TestCyclicBarrier
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/11
 * @Version V1.0
 **/
public class TestCyclicBarrier {
    public static void main(String[] args) {
//        CyclicBarrier barrier = new CyclicBarrier(5);
        /*CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("人满，发车");
            }
        });*/
        CyclicBarrier barrier = new CyclicBarrier(5,()->{
            System.out.println("人满，发车");
        });

        for(int i=0;i<20;i++){
            new Thread(()->{
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
