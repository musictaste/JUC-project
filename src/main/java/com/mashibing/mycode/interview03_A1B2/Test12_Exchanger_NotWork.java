package com.mashibing.mycode.interview03_A1B2;

import java.io.IOException;
import java.util.concurrent.Exchanger;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @ClassName Test06_CAS
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/17
 * @Version V1.0
 **/
public class Test12_Exchanger_NotWork {
    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) throws IOException {
        char[] numbers = {'1','2','3','4','5','6','7','8'};
        char[] letters ={'A','B','C','D','E','F','G','H'};

        new Thread(()->{
            for(char c:numbers) {
                System.out.print(c);
                try {
                    exchanger.exchange("T1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for(char c:letters) {
                try {
                    exchanger.exchange("T2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(c);
            }
        }).start();
    }
}
