package com.mashibing.mycode.readwriteLock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName TestReadWriteLock
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/11
 * @Version V1.0
 **/
public class TestReadWriteLock {
    public static Lock lock = new ReentrantLock();

    public static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public static Lock readLock = readWriteLock.readLock();
    public static Lock writeLock = readWriteLock.writeLock();

    private static int count =0;

    public static void read(Lock lock){
        try {
            lock.lock();
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("read over");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock,int value){
        try {
            lock.lock();
            count = value;
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("write over");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        TestReadWriteLock t = new TestReadWriteLock();

        Runnable read = ()->read(lock);
        Runnable write = ()->write(lock,new Random().nextInt());

//        Runnable read = ()->read(readLock);
//        Runnable write = ()->write(writeLock,new Random().nextInt());

        for (int i = 0; i < 18; i++) {
            new Thread(read).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(write).start();
        }
    }
}
