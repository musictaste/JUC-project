/**
 * ��Ҫ���ַ���������Ϊ��������
 * ������������У�m1��m2��ʵ��������ͬһ������
 * ����������ᷢ���ȽϹ�������󣬱������õ���һ����⣬�ڸ�����д����������ַ�����Hello����
 * �����������Դ�룬���������Լ��Ĵ�����Ҳ������"Hello",��ʱ����п��ܷ����ǳ����������������
 * ��Ϊ��ĳ�������õ�����ⲻ�����ʹ����ͬһ����
 * 
 * jetty
 * 
 * @author mashibing
 */
package com.mashibing.juc.c_017_MoreAboutSync;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DoNotLockString {
	
	String s1 = "Hello";
	String s2 = "Hello";

	void m1() {
		synchronized(s1) {
			try {
				System.out.println("m1 start");
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("m1 end");
		}
	}
	
	void m2() {
		synchronized(s2) {
			try {
				System.out.println("m2 start");
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("m2 end");
		}
	}

	public static void main(String[] args) {
		DoNotLockString object = new DoNotLockString();
		new Thread(()->object.m1(),"T1").start();
		new Thread(()->object.m2(),"T2").start();
	}

}
