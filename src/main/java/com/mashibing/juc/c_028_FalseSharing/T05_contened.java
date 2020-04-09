package com.mashibing.juc.c_028_FalseSharing;

import sun.misc.Contended;

/**
 * @ClassName T05_contened
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/29
 * @Version V1.0
 **/
public class T05_contened {
    @Contended
    volatile long x;
    @Contended
    volatile long y;

    public static void main(String[] args) throws InterruptedException {
        T05_contened t  = new T05_contened();
        Thread t1 = new Thread(()->{
            for (long i = 0; i < 1_0000_0000; i++) {
                t.x = i;
            }
        });

        Thread t2 = new Thread(()->{
            for (long i = 0; i < 1_0000_0000; i++) {
                t.y = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start)/100_0000);
    }

}
