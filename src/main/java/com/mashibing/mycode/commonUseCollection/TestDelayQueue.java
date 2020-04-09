package com.mashibing.mycode.commonUseCollection;

import java.sql.Time;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName DelayQueue
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/15
 * @Version V1.0
 **/
public class TestDelayQueue {
    static class MyTask implements Delayed {
        private String name;
        private long runningTime;

        public MyTask(String name, long runningTime) {
            this.name = name;
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)){
                return -1;
            }else if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)){
                return 1;
            }else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return name+"==="+runningTime;
        }
    }

    public static void main(String[] args) {
        BlockingQueue<MyTask> queue = new DelayQueue<>();
        long now = System.currentTimeMillis();
        MyTask task1 = new MyTask("t1",now+1000);
        MyTask task2 = new MyTask("t2",now+500);
        MyTask task3 = new MyTask("t3",now+1500);
        MyTask task4 = new MyTask("t4",now+2500);
        MyTask task5 = new MyTask("t5",now+2000);

        queue.add(task1);
        queue.add(task2);
        queue.add(task3);
        queue.add(task4);
        queue.add(task5);

        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
