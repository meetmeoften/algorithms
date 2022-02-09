package com;

public class OddEvenMain {

	public boolean odd;
	public int count = 1;
	public int MAX = 20;


	public void printOdd() {
		synchronized (this) {
			while (count < MAX) {
				while (!odd) {
					try {
						System.out.println("Odd waiting : " + count);
						wait();
						System.out.println("Notified odd :" + count);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("Odd Thread :" + count);
				count++;
				odd = false;
				notify();
			}
		}
	}

	public void printEven() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		synchronized (this) {
			while (count < MAX) {
				System.out.println("Checking even loop");
				while (odd) {
					try {
						System.out.println("Even waiting: " + count);
						wait();
						System.out.println("Notified even:" + count);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Even thread :" + count);
				count++;
				odd = true;
				notify();

			}
		}
	}


	public static void main(String[] args) {
		OddEvenMain oep = new OddEvenMain();
		oep.odd = true;

		Thread t1 = new Thread( new Runnable() {

			@Override
			public void run() {
				oep.printOdd();

			}
		});
	}
}
