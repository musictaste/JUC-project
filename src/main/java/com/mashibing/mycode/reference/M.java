package com.mashibing.mycode.reference;

/**
 * @ClassName M
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/15
 * @Version V1.0
 **/
public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
