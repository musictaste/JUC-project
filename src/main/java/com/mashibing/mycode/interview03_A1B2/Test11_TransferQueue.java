package com.mashibing.mycode.interview03_A1B2;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @ClassName Test06_CAS
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/17
 * @Version V1.0
 **/
public class Test11_TransferQueue {

    public static void main(String[] args) throws IOException {
        char[] numbers = {'1','2','3','4','5','6','7','8'};
        char[] letters ={'A','B','C','D','E','F','G','H'};

        TransferQueue<Character> queue = new LinkedTransferQueue<>();

        new Thread(()->{
            for(char c:numbers){
                try {
                    queue.transfer(c);
                    System.out.print(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T1").start();

        new Thread(()->{
            for(char c:letters){
                try {
                    System.out.print(queue.take());
                    queue.transfer(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T2").start();
    }
}
