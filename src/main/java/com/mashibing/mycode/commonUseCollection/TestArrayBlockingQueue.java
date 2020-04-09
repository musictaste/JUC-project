package com.mashibing.mycode.commonUseCollection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestArrayBlockingQueue
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/15
 * @Version V1.0
 **/
public class TestArrayBlockingQueue {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        for (int i = 0; i < 10; i++) {
            queue.offer(""+i);
        }

//        queue.offer("aaa");

//        queue.add("aaa");

        /*try {
            queue.put("aaa");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        try {
            queue.offer("aaa",1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
