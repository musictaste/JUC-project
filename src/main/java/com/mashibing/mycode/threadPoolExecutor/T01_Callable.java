package com.mashibing.mycode.threadPoolExecutor;

import java.util.concurrent.*;

/**
 * @ClassName T01_Callable
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/18
 * @Version V1.0
 **/
public class T01_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*Callable<String> call = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "callable";
            }
        };*/
        Callable<String> call = ()->{
            return "callable";
        };

        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(call);//异步
        String result = future.get();//阻塞
        System.out.println(result);

        service.shutdown();
    }
}
