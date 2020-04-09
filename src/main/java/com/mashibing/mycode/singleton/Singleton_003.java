package com.mashibing.mycode.singleton;

/**
 * DCL
 * double check lock
 * 最小的同步代码块，执行效率高，并且线程是安全的
 **/
public class Singleton_003 {
    public volatile static Singleton_003 INSTANCE;

    public Singleton_003(){}

    public static Singleton_003 getINSTANCE(){
        //其他业务代码
        if(INSTANCE ==null){
            synchronized (Singleton_003.class){
                if(INSTANCE ==null){
                    INSTANCE = new Singleton_003();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                System.out.println(Singleton_003.getINSTANCE().hashCode());
            }).start();
        }
    }
}
