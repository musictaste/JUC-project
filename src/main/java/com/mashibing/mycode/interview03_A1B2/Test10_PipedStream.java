package com.mashibing.mycode.interview03_A1B2;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @ClassName Test06_CAS
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/17
 * @Version V1.0
 **/
public class Test10_PipedStream {

    public static void main(String[] args) throws IOException {
        char[] numbers = {'1','2','3','4','5','6','7','8'};
        char[] letters ={'A','B','C','D','E','F','G','H'};

        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();
        PipedOutputStream output1 = new PipedOutputStream();
        PipedOutputStream output2 = new PipedOutputStream();

        input1.connect(output2);
        input2.connect(output1);

        String msg = "OK";

        new Thread(()->{
            byte[] buffer = new byte[2];
            try {
                for(char c:numbers){
                    input1.read(buffer);
                    if(new String(buffer).equals(msg)){
                        System.out.print(c);
                    }
                    output1.write(msg.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        },"T1").start();

        new Thread(()->{
            byte[] buffer = new byte[2];
            try {
                for(char c:letters){
                    System.out.print(c);
                    output2.write(msg.getBytes());

                    input2.read(buffer);
                    if(new String(buffer).equals(msg)){
                        continue;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        },"T2").start();
    }
}
