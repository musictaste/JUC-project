/**
 * 不要以字符串常量作为锁定对象
 * 在下面的例子中，m1和m2其实锁定的是同一个对象
 * 这种情况还会发生比较诡异的现象，比如你用到了一个类库，在该类库中代码锁定了字符串“Hello”，
 * 但是你读不到源码，所以你在自己的代码中也锁定了"Hello",这时候就有可能发生非常诡异的死锁阻塞，
 * 因为你的程序和你用到的类库不经意间使用了同一把锁
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
