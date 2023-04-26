package com.zjava8;


@FunctionalInterface
public interface Calculator {

	public int add(int a, int b);

	public default int subtract(int a, int b) {
		return a-b;
	}

}
