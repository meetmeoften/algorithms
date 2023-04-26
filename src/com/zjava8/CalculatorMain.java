package com.zjava8;

public class CalculatorMain {

	public static void main(String[] args) {
		Calculator addCalc = (a, b) -> a +b;

		System.out.println(addCalc.add(1, 2));

		Parent p = new Child();
		p.out();

		Parent p1 = new Parent();
		p1.out();
	}

}
