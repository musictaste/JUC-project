package com.mashibing.mycode;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName C_008
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/9
 * @Version V1.0
 **/
public class C_008 {
    private String name;
    private int balance;

    public synchronized void write(String name,int balance){
        this.name = name;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public int read(){
        return this.balance;
    }

    public static void main(String[] args) {
        C_008 t = new C_008();
        new Thread(()->t.write("test",100)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t.balance);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t.balance);
    }
}
