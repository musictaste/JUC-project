package com.mashibing.mycode.reference;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestSoftReference
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/15
 * @Version V1.0
 **/
public class TestSoftReference {
    public static void main(String[] args) {
        SoftReference<byte[]> r = new SoftReference<>(new byte[1024*1024*10]);

        System.out.println(r.get());
        System.gc();

        byte[] another = new byte[1024*1024*15];
        System.out.println(r.get());

    }
}
