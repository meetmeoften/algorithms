package com.test.matrix;

public class RectangleClass {

	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
	public static void rectanglePatternPrint(int n) {
		int row, column;
		// INSERT YOUR CODE HERE
		if (n % 2 == 0) {
			row = n + 1;
			column = n + 1;
		} else {
			row = n + 1;
			column = n;
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (i > 0 && j == column / 2) {
					System.out.print(i);
				}
				else {
					System.out.print(n);
				}
			}
			System.out.println();
		}

	}

	public static void main(String[] args) {
		rectanglePatternPrint(4);
	}

}
