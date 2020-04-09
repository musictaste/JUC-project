package com.mashibing.mycode;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SetDaemon
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/9
 * @Version V1.0
 **/
public class SetDaemon extends Thread{
    private int count =0;
    @Override
    public void run() {
        while(true){
            count++;
            System.out.println(Thread.currentThread().getName()+" count:"+count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        SetDaemon o = new SetDaemon();
        Thread t = new Thread(o,"t1");
        t.setDaemon(true);
        t.start();
        System.out.println("主线程 start");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程 end");

    }
}
