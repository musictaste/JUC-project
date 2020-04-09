package com.mashibing.mycode.singleton;

/**
 * 饿汉式
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用，推荐使用！
 * 唯一缺点：不管用到与否，类装载时就完成实例化
 * Class.forName("")
 * （话说你不用的，你装载它干啥）
 */
public class Singleton_001 {
    public static final Singleton_001 INSTANCE = new Singleton_001();

    private Singleton_001(){}

    public static Singleton_001 getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
        Singleton_001 s1 = Singleton_001.getInstance();
        Singleton_001 s2 = Singleton_001.getInstance();
        System.out.println(s1==s2);
    }
}
