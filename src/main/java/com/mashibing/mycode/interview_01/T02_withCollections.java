package com.mashibing.mycode.interview_01;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName T01_withVolatile
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/12
 * @Version V1.0
 **/
public class T02_withCollections {
    volatile List<Object> list = Collections.synchronizedList(new LinkedList<>());

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T02_withCollections o = new T02_withCollections();

        new Thread(()->{
            while(true){
                if(o.size()==5){
                    break;
                }
            }
            System.out.println("T2...end");
        }).start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                o.add(new Object());
                System.out.println("add  "+i);
            }
        },"T1").start();
    }
}
