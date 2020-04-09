package com.mashibing.mycode.threadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T07_CachedThreadPool
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/18
 * @Version V1.0
 **/
public class T07_CachedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);
        for (int i = 0; i < 2; i++) {
            service.execute(()->{
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);

        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(service);

    }
}
