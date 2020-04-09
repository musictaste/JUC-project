package com.mashibing.mycode.interview_01;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName T01_withVolatile
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/12
 * @Version V1.0
 **/
public class T09_Semaphore {
    static Thread t1=null, t2 =null;

    /*volatile*/ List<Object> list = new LinkedList<>();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T09_Semaphore o = new T09_Semaphore();

        Semaphore s = new Semaphore(1);

        t1 = new Thread(()->{
            System.out.println("T1 start");
            try {
                s.acquire();
                for (int i = 0; i < 5; i++) {
                    o.add(new Object());
                    System.out.println("add  "+i);
                }
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                t2.start();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                s.acquire();
                for (int i = 5; i < 10; i++) {
                    o.add(new Object());
                    System.out.println("add  "+i);
                }
                s.release();
            } catch (Exception e) {
                e.printStackTrace();
            }

        },"T1");

        t2 = new Thread(()->{
            try {
                System.out.println("T2 start");
                s.acquire();
                System.out.println("T2...end");
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
    }
}
