package com.mashibing.mycode.interview03_A1B2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Test04_LockAndCondition
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/17
 * @Version V1.0
 **/
public class Test04_LockAndCondition {
    public static void main(String[] args) {
        char[] numbers = {'1','2','3','4','5','6','7','8'};
        char[] letters ={'A','B','C','D','E','F','G','H'};
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(()->{
            try {
                lock.lock();
                for(char c:numbers){
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"T1").start();

        new Thread(()->{
            try {
                lock.lock();

                for(char c:letters){
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"T2").start();

    }

}
