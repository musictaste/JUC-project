package com.mashibing.mycode.commonUseCollection;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @ClassName TestConcurrentQueue
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/15
 * @Version V1.0
 **/
public class TestConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.offer("a"+i);
        }

        System.out.println(queue.size());

        String poll = queue.poll();
        System.out.println(poll);
        System.out.println(queue.size());

        String peek = queue.peek();
        System.out.println(peek);
        System.out.println(queue.size());

    }
}
