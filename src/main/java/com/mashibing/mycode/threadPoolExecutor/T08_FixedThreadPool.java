package com.mashibing.mycode.threadPoolExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName T08_FixedThreadPool
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/18
 * @Version V1.0
 **/
public class T08_FixedThreadPool {
    static boolean isPrime(int num){
        for (int i = 2; i < num/2; i++) {
            if(num%i == 0) return false;
        }
        return true;
    }

    static List<Integer> getPrimes(int start, int end){
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <=end; i++) {
            if(isPrime(i)) list.add(i);
        }
        return list;
    }

    static class Mytask implements Callable<List<Integer>>{
        int start,end;

        public Mytask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public List<Integer> call() throws Exception {
            return getPrimes(start,end);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start,end;
        start = System.currentTimeMillis();
        getPrimes(0,200000);
        end = System.currentTimeMillis();
        System.out.println(end-start);

        final int cpuCoreNum = 4;//CPU的核心线程数为4，我本机是6核12线程
        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);
        Mytask task1 = new Mytask(0,50000);
        Mytask task2 = new Mytask(50001,100000);
        Mytask task3 = new Mytask(100001,150000);
        Mytask task4 = new Mytask(150001,200000);

        Future<List<Integer>> f1= service.submit(task1);
        Future<List<Integer>> f2=service.submit(task2);
        Future<List<Integer>> f3=service.submit(task3);
        Future<List<Integer>> f4=service.submit(task4);

        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println(end-start);
        service.shutdown();
    }
}
