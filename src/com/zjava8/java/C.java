package com.zjava8.java;

public class C extends B {

	@Override
	public int add(int a, int b) {
		System.out.println("C");
		return 2 * (a+b);
	}

}
