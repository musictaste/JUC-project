package com.mashibing.mycode.threadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T04_ExecutorService
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/18
 * @Version V1.0
 **/
public class T04_ExecutorService {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 6; i++) {
            service.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);

        service.shutdown();

        //执行1
        /*System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());*/

        //执行2
        TimeUnit.SECONDS.sleep(5);
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());

        System.out.println(service);

    }
}
