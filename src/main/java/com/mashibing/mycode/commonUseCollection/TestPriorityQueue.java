package com.mashibing.mycode.commonUseCollection;

import java.util.PriorityQueue;

/**
 * @ClassName TestPriorityQueue
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/15
 * @Version V1.0
 **/
public class TestPriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add("e");
        queue.add("b");
        queue.add("a");
        queue.add("d");
        queue.add("c");

        int size  = queue.size();
        for (int i = 0; i < size; i++) {
            System.out.println(queue.poll());
        }
    }

}
