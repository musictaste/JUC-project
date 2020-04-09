package com.mashibing.mycode.interview03_A1B2;

/**
 * @ClassName T02_WaitNotify
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/17
 * @Version V1.0
 **/
public class Test03_WaitNotify_Boolean {
    static volatile boolean t2First = true;

    public static void main(String[] args) {
        char[] numbers = {'1','2','3','4','5','6','7','8'};
        char[] letters ={'A','B','C','D','E','F','G','H'};
        Object o = new Object();

        new Thread(()->{
            synchronized (o){
                while(t2First){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(char c:numbers){
                    try {
                        System.out.print(c);
                        o.notify();
                        o.wait();//让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();//必须，否则无法停止程序
            }
        },"T1").start();

        new Thread(()->{
            synchronized (o){
                for(char c:letters){
                    try {
                        System.out.print(c);
                        t2First=false;
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                o.notify();
            }
        },"T2").start();

    }
}
