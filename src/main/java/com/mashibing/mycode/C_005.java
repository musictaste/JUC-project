package com.mashibing.mycode;

/**
 * @ClassName C_005
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/9
 * @Version V1.0
 **/
public class C_005 {
    private /*volatile*/ int count =10;

    public synchronized void m(){
        count--;
        System.out.println(Thread.currentThread().getName()+",count:"+count);
    }

    public static void main(String[] args) {
        C_005 t = new C_005();
        for(int i=0;i<10;i++){
            new Thread(t::m,"Thread"+i).start();

//            new Thread(()->t.m(),"Thread"+i).start();

            //1.8之前的写法
            /*new Thread(new Runnable() {
                @Override
                public void run() {
                    t.m();
                }
            },"Thread"+i).start();*/
        }
    }

}
