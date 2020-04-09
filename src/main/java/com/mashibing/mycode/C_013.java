package com.mashibing.mycode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName C_013
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/10
 * @Version V1.0
 **/
public class C_013 {
    private volatile int count =0;

    public /*synchronized*/ void m (){
        for(int i=0;i<10000;i++){
            count++;
        }
    }

    public static void main(String[] args) {
        C_013 t = new C_013();

        List<Thread> threadList = new ArrayList<>();
        for(int i=0;i<10;i++){
            threadList.add(new Thread(t::m));
        }
        threadList.forEach((o)->o.start());

        threadList.forEach((o)-> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}
