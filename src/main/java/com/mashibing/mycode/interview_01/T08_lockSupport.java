package com.mashibing.mycode.interview_01;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName T01_withVolatile
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/12
 * @Version V1.0
 **/
public class T08_lockSupport {
    static Thread t1=null, t2 =null;

    /*volatile */List<Object> list = new LinkedList<>();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T08_lockSupport o = new T08_lockSupport();

        t1 = new Thread(()->{
            System.out.println("T1 start");
            for (int i = 0; i < 10; i++) {
                o.add(new Object());
                System.out.println("add  "+i);
                if(o.size()==5){
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        },"T1");

        t2 = new Thread(()->{
            System.out.println("T2 start");
            /*if(o.size() !=5){
                LockSupport.park();
            }*/
            LockSupport.park();
            System.out.println("T2...end");
            LockSupport.unpark(t1);
        });

        t2.start();
        t1.start();
    }
}
