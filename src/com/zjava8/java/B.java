package com.zjava8.java;

public class B extends A {

	@Override
	public int add(int a, int b) {
		System.out.println("B");
		return a*b;
	}

}
