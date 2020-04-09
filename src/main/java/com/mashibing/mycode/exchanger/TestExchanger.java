package com.mashibing.mycode.exchanger;

import javax.sound.midi.Soundbank;
import java.util.concurrent.Exchanger;

/**
 * @ClassName TestExchanger
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/11
 * @Version V1.0
 **/
public class TestExchanger {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {

        new Thread(()->{
            String s = "t1";
            try {
                s =  exchanger.exchange(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" "+s);
        },"T1").start();

        new Thread(()->{
            String s = "t2";
            try {
                s =  exchanger.exchange(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" "+s);
        },"T2").start();
    }
}
