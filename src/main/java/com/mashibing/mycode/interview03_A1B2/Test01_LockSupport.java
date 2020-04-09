package com.mashibing.mycode.interview03_A1B2;

import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName Test01_LockSupport
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/17
 * @Version V1.0
 **/
public class Test01_LockSupport {
    static Thread t1=null,t2 =null;

    public static void main(String[] args) {
        char[] numbers = {'1','2','3','4','5','6','7','8'};
        char[] letters ={'A','B','C','D','E','F','G','H'};

        t1 = new Thread(()->{
            for (int i = 0; i < numbers.length; i++) {
                System.out.print(numbers[i]);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        },"T1");

        t2 = new Thread(()->{
            for (int i = 0; i < letters.length; i++) {
                LockSupport.park();
                System.out.print(letters[i]);
                LockSupport.unpark(t1);
            }
        },"T2");

        t1.start();
        t2.start();
    }

}
