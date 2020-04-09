package com.mashibing.mycode.threadPoolExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName T02_FutureTask
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/18
 * @Version V1.0
 **/
public class T02_FutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task  = new FutureTask<>(()->{
           return 100;
        });

        new Thread(task).start();
        System.out.println(task.get());//阻塞
    }
}
