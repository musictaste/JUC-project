package com.mashibing.mycode.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName T001
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/15
 * @Version V1.0
 **/
public class T001 {
    static class Person{
        String name ="zhangsan";

        public Person(String name){
            this.name=name;
        }
    }

    static ThreadLocal<Person> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(()->{
            threadLocal.set(new Person("lisi"));
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadLocal.get());
        }).start();


    }

}
