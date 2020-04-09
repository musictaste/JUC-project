package com.mashibing.mycode.singleton;

/**
 * lazy loading
 * 也称懒汉式
 * 虽然达到了按需初始化的目的，但却带来线程不安全的问题
 * 可以通过synchronized解决，但也带来效率下降
 */
public class Singleton_002 {

    public static Singleton_002 INSTANCE;

    private Singleton_002(){}

    public static synchronized Singleton_002 getInstance(){
        if(INSTANCE ==null){
            INSTANCE = new Singleton_002();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i =0;i<10;i++){
            new Thread(()->{
                System.out.println(Singleton_002.getInstance().hashCode());
            }).start();
        }
    }
}
