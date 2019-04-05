package com.test.general;

public class B extends A {
	int j;

	void display() {
		super.j = 3;
		super.i = 10;
		System.out.println(i + " " + j);
	}
}
