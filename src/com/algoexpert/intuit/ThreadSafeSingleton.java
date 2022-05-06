package com.algoexpert.intuit;

public class ThreadSafeSingleton {

	private static volatile ThreadSafeSingleton helper;

	public ThreadSafeSingleton getHelper() {
		if (helper == null) {
			synchronized (ThreadSafeSingleton.class) {
				if (helper == null) {
					helper = new ThreadSafeSingleton();
				}
			}
		}
		return helper;
	}


}
