package com.algoexpert.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SquareOfZeros {

	public static boolean squareOfZeroes(List<List<Integer>> matrix) {
		int n = matrix.size();
		for (int topRow = 0; topRow < n; topRow++) {
			for (int leftCol = 0; leftCol < n; leftCol++) {
				int square = 2;
				while (square <= n - leftCol && square <= n - topRow) {
					int bottomRow = topRow + square - 1;
					int rightCol = leftCol + square - 1;
					if (isSquareOfZeros(matrix, topRow, leftCol, bottomRow, rightCol)) {
						return true;
					}
					square++;
				}
			}
		}
		return false;
	}

	private static boolean isSquareOfZeros(List<List<Integer>> matrix, int r1, int c1, int r2, int c2) {
		for (int row = r1; row < r2 + 1; row++) {
			if (matrix.get(row).get(c1) != 0 || matrix.get(row).get(c2) != 0) {
				return false;
			}
		}

		for (int col = c1; col < c2 + 1; col++) {
			if (matrix.get(r1).get(col) != 0 || matrix.get(r2).get(col) != 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		List<List<Integer>> test = new ArrayList<List<Integer>>();
		test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 1, 1, 0, 1, 0 })));
		test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] { 0, 0, 0, 0, 0, 1 })));
		test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] { 0, 1, 1, 1, 0, 1 })));
		test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] { 0, 0, 0, 1, 0, 1 })));
		test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] { 0, 1, 1, 1, 0, 1 })));
		test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] { 0, 0, 0, 0, 0, 1 })));
		squareOfZeroes(test);
	}

}
