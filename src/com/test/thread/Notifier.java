package com.test.thread;

public class Notifier implements Runnable {

	private final Message msg;

	public Notifier(Message msg) {
		this.msg = msg;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name+" started");
		try {
			Thread.sleep(1000);
			synchronized (msg) {
				msg.setMsg(name+" Notifier work done");
				System.out.println("Notfier about to notify");
				msg.notify();
				// msg.notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
