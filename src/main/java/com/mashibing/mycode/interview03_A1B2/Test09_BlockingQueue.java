package com.mashibing.mycode.interview03_A1B2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @ClassName Test06_CAS
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/17
 * @Version V1.0
 **/
public class Test09_BlockingQueue {

    public static void main(String[] args) {
        char[] numbers = {'1','2','3','4','5','6','7','8'};
        char[] letters ={'A','B','C','D','E','F','G','H'};

        BlockingQueue<String> queue1 = new ArrayBlockingQueue(1);
        BlockingQueue<String> queue2 = new ArrayBlockingQueue(1);

        new Thread(()->{
            for(char c:numbers){
                System.out.print(c);
                try {
                    queue1.put("ok");
                    queue2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T1").start();

        new Thread(()->{
            for(char c:letters){
                try {
                    queue1.take();
                    System.out.print(c);
                    queue2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T2").start();
    }
}
