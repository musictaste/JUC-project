package com.mashibing.mycode.commonUseCollection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @ClassName SynchronusQueue
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/15
 * @Version V1.0
 **/
public class SynchronusQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

//        queue.put("aaa");
//        queue.add("aaa");
        System.out.println(queue.size());
    }

}
