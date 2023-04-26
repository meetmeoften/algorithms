package com.zjava8;

public class FormulaMain {

	public static void main(String[] args) {
		Formula formula = (a) -> {
			System.out.println(a);
			return 1.0;
		};

		formula.calculate(1);


		Formula formula1 = new Formula() {

			@Override
			public double calculate(int a) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}




}
