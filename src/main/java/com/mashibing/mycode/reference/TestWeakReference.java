package com.mashibing.mycode.reference;

import java.lang.ref.WeakReference;

/**
 * @ClassName TestWeakReference
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/15
 * @Version V1.0
 **/
public class TestWeakReference {
    public static void main(String[] args) {
        WeakReference<M> r = new WeakReference<>(new M());
        System.out.println(r.get());

        System.gc();
        System.out.println(r.get());

        ThreadLocal<M> t = new ThreadLocal<>();
        t.set(new M());
        t.remove();
    }
}
