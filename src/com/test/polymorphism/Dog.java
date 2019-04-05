package com.test.polymorphism;

public class Dog extends Animal {

	public int i = 20;

	@Override
	public void name()  {
		System.out.println("Dog");
	}

	public void method1(String method) {
		System.out.println("Method");

	}

	public void method(int i, float floa) {
		System.out.println("Method1");
	}

	public void method(float i, int floa) {
		System.out.println("Method2");
	}


}
