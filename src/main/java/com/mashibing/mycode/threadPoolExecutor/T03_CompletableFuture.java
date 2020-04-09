package com.mashibing.mycode.threadPoolExecutor;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName T03_CompletableFuture
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/18
 * @Version V1.0
 **/
public class T03_CompletableFuture {
    public static void delay(){
        int time  = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("after %s sleep\n",time);
    }

    public static double priceOfTB(){
        delay();
        return 1.00;
    }

    public static double priceOfJD(){
        delay();
        return 2.00;
    }

    public static double priceOfPDD(){
        delay();
        return 2.50;
    }

    public static void main(String[] args) {
        long start,end;

        start = System.currentTimeMillis();

        CompletableFuture<Double> future_JD  = CompletableFuture.supplyAsync(()->priceOfJD());
        CompletableFuture<Double> future_TB  = CompletableFuture.supplyAsync(()->priceOfTB());
        CompletableFuture<Double> future_PDD  = CompletableFuture.supplyAsync(()->priceOfPDD());

        CompletableFuture.allOf(future_JD,future_TB,future_PDD).join();

        CompletableFuture.supplyAsync(()->priceOfJD())
                .thenApply(String::valueOf)
                .thenApply(str-> "price:"+str)
                .thenAccept(System.out::println);

        end = System.currentTimeMillis();

        System.out.println("complete future need time "+(end-start));

        try {
            System.in.read();//
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
