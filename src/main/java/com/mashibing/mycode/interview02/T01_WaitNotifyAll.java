package com.mashibing.mycode.interview02;

import java.util.LinkedList;

/**
 * @ClassName T01_WaitNotifyAll
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/12
 * @Version V1.0
 **/
public class T01_WaitNotifyAll<T> {
    private LinkedList<T> list = new LinkedList<>();
    final private int MAX =10;
    private int count =0;

    public synchronized T get(){
        T t =null;
        while (list.size()==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        t= list.removeFirst();
        count--;
        this.notifyAll();//通知生产者进行生产
        return t;
    }

    public synchronized void put(T t){
        //想想为什么用while而不是用if？
        //当线程被叫醒，还需要再次判断
        // 如果不判断直接执行，现在MAX=10,应该是不行执行，但是使用了if程序就会继续执行，size>10
        while (list.size()==MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list.add(t);
        count++;
        this.notifyAll();//通知消费者线程进行消费
    }

    public static void main(String[] args) {
        T01_WaitNotifyAll<String> object = new T01_WaitNotifyAll<>();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    String t = object.get();
                    System.out.println(Thread.currentThread().getName()+" "+t);
                }
            },"customer "+i).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    object.put(Thread.currentThread().getName()+" "+j);
                }
            },"produce "+i).start();
        }
    }
}
