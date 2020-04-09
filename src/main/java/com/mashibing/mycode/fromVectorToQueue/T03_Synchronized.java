package com.mashibing.mycode.fromVectorToQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T01_ArrayList
 * @Description: TODO
 * @Author ���
 * @Date 2020/3/15
 * @Version V1.0
 **/
public class T03_Synchronized {
    public static List<String> list = new LinkedList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            list.add("��Ʊ��ţ�"+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                synchronized (list){
                    while (list.size()>0){
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("������"+list.remove(0));
                    }
                }
            }).start();
        }
    }
}
