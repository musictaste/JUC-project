package com.mashibing.mycode.commonUseCollection;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName TestCopyOnWriteList
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/15
 * @Version V1.0
 **/
public class TestCopyOnWriteList {
    public static void main(String[] args) {
        List<UUID> list = new CopyOnWriteArrayList<>();

        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    list.add(UUID.randomUUID());
                }
            });
        }


        long startTime = System.currentTimeMillis();
        Arrays.asList(threads).forEach(t->t.start());
        Arrays.asList(threads).forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long endTime = System.currentTimeMillis();
        System.out.println("写时间："+(endTime-startTime));
        System.out.println(list.size());

        //==========================
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
               list.get(10);
            });
        }

        startTime = System.currentTimeMillis();
        Arrays.asList(threads).forEach(t->t.start());
        Arrays.asList(threads).forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        endTime = System.currentTimeMillis();
        System.out.println("读时间："+(endTime-startTime));
    }


}
