package com.mashibing.mycode;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName C_010
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/9
 * @Version V1.0
 **/
public class C_010 {
    synchronized void m(){
        System.out.println("super start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("supper end");

    }

    public static void main(String[] args) {
        new Thread(()->new TT().mm()).start();
    }

}
class TT extends C_010{
     synchronized void mm(){
        System.out.println("child start");
        super.m();
        System.out.println("child end");
    }
}
