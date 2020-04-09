package com.mashibing.mycode;

import javax.sound.midi.Soundbank;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName C_009
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/9
 * @Version V1.0
 **/
public class C_009 {
    synchronized void m1(){
        System.out.println("m1 start");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        m2();
        System.out.println("m1 end");
    }

    synchronized void m2(){
        System.out.println("m2");
    }

    public static void main(String[] args) {
        C_009 t = new C_009();
        new Thread(t::m1).start();
    }
}
