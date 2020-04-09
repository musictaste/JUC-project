package com.mashibing.mycode.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName PhantomReference
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/15
 * @Version V1.0
 **/
public class TestPhantomReference {
    static List<Object> list = new LinkedList<>();
    static ReferenceQueue<M> queue = new ReferenceQueue<>();

    public static void main(String[] args) {
        PhantomReference<M> r = new PhantomReference<>(new M(),queue);
        new Thread(()->{
            while (true){
                list.add(new byte[1024*1024]);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(r.get());
            }
        }).start();

        new Thread(()->{
           while (true){
               Reference<? extends M> poll =queue.poll();
               if(poll !=null){
                   System.out.println("---虚引用对象被JVM回收---"+poll);
               }
           }
        }).start();
    }
}
