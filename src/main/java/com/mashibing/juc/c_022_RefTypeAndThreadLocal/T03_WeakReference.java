/**
 * 弱引用遭到gc就会回收
 *
 */
package com.mashibing.juc.c_022_RefTypeAndThreadLocal;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class T03_WeakReference {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());
        System.gc();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());


       /* ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();*/

    }
}

