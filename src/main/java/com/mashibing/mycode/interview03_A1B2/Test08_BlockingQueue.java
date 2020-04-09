package com.mashibing.mycode.interview03_A1B2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Test06_CAS
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/17
 * @Version V1.0
 **/
public class Test08_BlockingQueue {

    public static void main(String[] args) {
        char[] numbers = {'1','2','3','4','5','6','7','8'};
        char[] letters ={'A','B','C','D','E','F','G','H'};

        BlockingQueue queue1 = new ArrayBlockingQueue(1);
        BlockingQueue queue2 = new ArrayBlockingQueue(1);

        new Thread(()->{
            for(char c:numbers){
                try {
                    queue2.put(c);
                    System.out.print(queue1.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T1").start();

        new Thread(()->{
            for(char c:letters){
                try {
                    System.out.print(queue2.take());
                    queue1.put(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T2").start();
    }
}
