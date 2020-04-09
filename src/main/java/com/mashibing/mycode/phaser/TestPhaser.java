package com.mashibing.mycode.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestPhaser
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/11
 * @Version V1.0
 **/
public class TestPhaser {
    static MarriagePhaser phaser = new MarriagePhaser();

    static void millisSleep(int millis){
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        phaser.bulkRegister(7);
        for(int i=0;i<5;i++){
            new Person("p"+i).start();
        }
        new Person("新郎").start();
        new Person("新娘").start();
    }

    static class MarriagePhaser extends Phaser{
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase){
                case 0:
                    System.out.println("所有人到齐了！人数为："+registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人吃完了！人数为："+registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人离开了！人数为："+registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束，新郎新娘抱抱！人数为："+registeredParties);
                    System.out.println();
                    return true;
                default:
                    return true;

            }
        }
    }

    static class Person extends Thread{
        private String name;
        public Person(String name){
            this.name = name;
        }
        public void arrive(){
            millisSleep(1000);
            System.out.printf("%s 到达现场!\n",name);
            phaser.arriveAndAwaitAdvance();
        }

        public void eat(){
            millisSleep(1000);
            System.out.printf("%s 吃完！\n",name);
            phaser.arriveAndAwaitAdvance();
        }

        public void leave(){
            millisSleep(1000);
            System.out.printf("%s 离开！\n",name);
            phaser.arriveAndAwaitAdvance();
        }

        public void hug(){
            if(name.equals("新郎") || name.equals("新娘")){
                millisSleep(1000);
                System.out.printf("%s 拥抱！\n",name);
                phaser.arriveAndAwaitAdvance();
            }else{
                phaser.arriveAndDeregister();
//                phaser.register();
            }
        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }
}
