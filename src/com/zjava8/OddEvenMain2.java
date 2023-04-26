package com.zjava8;

public class OddEvenMain2 implements Runnable {

	public static final int MAX_COUNT = 10;
	public int rem = 0;
	static Object lock = new Object();
	static int number = 1;
	public String name = "";


	public OddEvenMain2(int rem, String name) {
		this.rem = rem;
		this.name = name;
	}

	@Override
	public void run() {
		while(number  < MAX_COUNT ) {
			synchronized (lock) {
				if(number % 2 != rem) {
					try {
						System.out.println("WAITING 1 " + " " + name);
						lock.wait();
						System.out.println("WAITING 2 " + " " + name);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(number + " " + name);
				number++;
				lock.notifyAll();
				System.out.println("NOTIFIED " + " " + name);
			}
		}
	}


	public static void main(String[] args) {
		OddEvenMain2 odd = new OddEvenMain2(1, "T1");
		OddEvenMain2 even = new OddEvenMain2(0, "T2");

		Thread t1 = new Thread(odd);
		Thread t2 = new Thread(even);

		t1.start();
		t2.start();
	}



}

