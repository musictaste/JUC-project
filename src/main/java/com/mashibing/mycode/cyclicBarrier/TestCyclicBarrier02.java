package com.mashibing.mycode.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static com.google.common.collect.ComparisonChain.start;


/**
 * @ClassName TestCyclicBarrier02
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/11
 * @Version V1.0
 **/
public class TestCyclicBarrier02{
    public void m(String name){
        System.out.println(name+" 任务执行");
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3,()->{
            System.out.println("模拟结束");
        });

        TestCyclicBarrier02 t = new TestCyclicBarrier02();
        new Thread(()->{
            try {
                t.m("读数据库");
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                t.m("读文件");
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                t.m("读网络数据");
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
