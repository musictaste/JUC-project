package com.mashibing.mycode;

/**
 * @ClassName C_002
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/9
 * @Version V1.0
 **/
public class C_002 {
    static class T1 extends Thread{
        @Override
        public void run() {
            System.out.println("T1 start");
        }
    }

    static class T2 implements Runnable{
        @Override
        public void run() {
            System.out.println("T2 start");
        }
    }

    public static void main(String[] args) {
        new T1().start();
        new Thread(new T2()).start();
        new Thread(()->{
            System.out.println("T3 start");
        }).start();
    }
}
