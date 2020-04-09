package com.mashibing.mycode.lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName TestLockSupport
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/11
 * @Version V1.0
 **/
public class TestLockSupport {
    public static void main(String[] args) {
        Thread t= new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(i +"======="+System.currentTimeMillis());
                if(i == 5) {
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
//        LockSupport.unpark(t); //unpark 先于park前调用，i==5,则不会进入阻塞状态

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(t);
    }
}
