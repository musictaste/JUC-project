package com.mashibing.mycode.reference;

import java.io.IOException;

/**
 * @ClassName NormalReference
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/3/15
 * @Version V1.0
 **/
public class TestNormalReference {
    public static void main(String[] args) {
        M m = new M();
        m = null;
        System.gc();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
