package com.mashibing.mycode.interview03_A1B2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Test06_CAS
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/17
 * @Version V1.0
 **/
public class Test07_AtomicInteger {
    static AtomicInteger threadNumber = new AtomicInteger(1);

    public static void main(String[] args) {
        char[] numbers = {'1','2','3','4','5','6','7','8'};
        char[] letters ={'A','B','C','D','E','F','G','H'};

        new Thread(()->{
            for(char c:numbers){
                while (threadNumber.get() !=1){}

                System.out.print(c);
                threadNumber.set(2);
            }
        },"T1").start();

        new Thread(()->{
            for(char c:letters){
                while (threadNumber.get() != 2){}
                System.out.print(c);
                threadNumber.set(1);
            }
        },"T2").start();
    }
}
