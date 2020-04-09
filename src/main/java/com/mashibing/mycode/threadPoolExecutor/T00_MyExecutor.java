package com.mashibing.mycode.threadPoolExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName T00_MyExecutor
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/18
 * @Version V1.0
 **/
public class T00_MyExecutor implements Executor {
    public static void main(String[] args) {
        new T00_MyExecutor().execute(()->{
            System.out.println("executor execute");
        });

        ExecutorService executorService = Executors.newCachedThreadPool();
    }

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
