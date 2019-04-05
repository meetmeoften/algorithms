package com.test.thread;

public class ThreadSynchron {

	Object lock = new Object();

	public synchronized void m1() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ie) {
		}
	}

	public  void m2() {
		synchronized(new Object()) {
			try {
				Thread.sleep(2000);
				System.out.println("Completed");
			} catch (InterruptedException ie) {
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final ThreadSynchron t = new ThreadSynchron();
		//		final ThreadSynchron ts = new ThreadSynchron();
		Thread t1 = new Thread() {
			@Override
			public void run() {
				t.m1();
			}
		};

		Thread t2 = new Thread() {
			@Override
			public void run() {
				t.m2();
			}
		};

		t1.start();
		Thread.sleep(500);

		t2.start();
		t2.join();

		System.out.println("State " + t2.getState());
	}
}