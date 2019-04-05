package com.test.general;

public class Shape {

	public void draw() {
		System.out.println("Drawing Shape");
	}

	public static void main(String[] args) {
		Shape s = new Rectangle();
		s.draw();

		s = new Circle();
		s.draw();
	}

}

class Rectangle extends Shape {
	@Override
	public void draw() {
		System.out.println("Drawing Rectangle");
		super.draw();
	}
}

class Circle extends Shape {
	@Override
	public void draw() {
		System.out.println("Drawing Circle");
	}
}
