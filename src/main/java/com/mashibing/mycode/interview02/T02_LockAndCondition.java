package com.mashibing.mycode.interview02;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName T02_LockAndCondition
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/12
 * @Version V1.0
 **/
public class T02_LockAndCondition<T> {
    private LinkedList<T> list = new LinkedList<>();
    final private int MAX = 10;
    private int count =0;

    private Lock lock = new ReentrantLock();
    private Condition produceCondition = lock.newCondition();
    private Condition customerCondition = lock.newCondition();

    public T get(){
        T t =null;
        try {
            lock.lock();
            while (list.size()==0){
                customerCondition.await();
            }
            t= list.removeFirst();
            count--;
            produceCondition.signalAll();//通知生产者进行生产
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public void put(T t){
        try {
            lock.lock();
            while (list.size()==MAX){//想想为什么用while而不是用if？
                produceCondition.await();
            }
            list.add(t);
            count++;
            customerCondition.signalAll();//通知消费者线程进行消费
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T02_LockAndCondition<String> object = new T02_LockAndCondition<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName()+" "+object.get());
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
