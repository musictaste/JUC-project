package com.mashibing.mycode.interview03_A1B2;

import com.mashibing.juc.c_003.T1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Test06_CAS
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/17
 * @Version V1.0
 **/
public class Test06_CAS {
    enum ReadyToRun{T1,T2};

    static volatile ReadyToRun r = ReadyToRun.T1;

    public static void main(String[] args) {
        char[] numbers = {'1','2','3','4','5','6','7','8'};
        char[] letters ={'A','B','C','D','E','F','G','H'};

        new Thread(()->{
            for(char c:numbers){
                while (!r.equals(ReadyToRun.T1)){}

                System.out.print(c);
                r = ReadyToRun.T2;
            }
        },"T1").start();

        new Thread(()->{
            for(char c:letters){
                while (!r.equals(ReadyToRun.T2)){}
                System.out.print(c);
                r = ReadyToRun.T1;
            }
        },"T2").start();
    }
}
