package com.zjava8;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OddEvenMain3 implements Runnable {

	private String name;
	private int value;
	private static final int MAX_COUNT = 10;
	private static Object lock = new Object();
	static int number = 1;


	public OddEvenMain3(int value, String name) {
		this.name = name;
		this.value = value;
	}

	@Override
	public void run() {
		while(number  < MAX_COUNT ) {
			synchronized (lock) {
				if(number % 2 != value) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(name + " " + number);
				number++;
				lock.notifyAll();
			}

		}

	}

	public static void main(String[] args) {
		OddEvenMain3 o1 = new OddEvenMain3(1, "T1");
		OddEvenMain3 o2 = new OddEvenMain3(0, "T2");

		Thread t1 = new Thread(o1);
		Thread t2 = new Thread(o2);

		t1.start();
		t2.start();


		List<Integer> list = Stream.iterate(new int[] {0,1}, t -> new int[] {t[1] , t[0] + t[1]}).limit(10).map(t -> t[0]).collect(Collectors.toList());
		System.out.println(list);
	}


}
