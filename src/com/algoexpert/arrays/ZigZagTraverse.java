package com.algoexpert.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZigZagTraverse {


	public static List<Integer> zigzagTraverse(List<List<Integer>> array) {
		int startRow = 0;
		int endRow = array.size()-1;
		int startCol = 0;
		int endCol = array.get(0).size() -1;
		List<Integer> result = new ArrayList<>();

		boolean goingDown = true;

		while(!isOutOfBounds(startRow, startCol, endRow, endCol)) {
			System.out.println(array.get(startRow).get(startCol));
			result.add(array.get(startRow).get(startCol));
			if(goingDown) {
				if(startCol == 0 || startRow == endRow) {
					goingDown = false;
					if(startRow == endRow) {
						startCol++;
					} else {
						startRow++;
					}
				} else {
					startRow++;
					startCol--;
				}
			} else {
				if(startRow == 0 || startCol == endCol) {
					goingDown = true;
					if(startCol == endCol) {
						startRow++;
					} else {
						startCol++;
					}
				} else {
					startRow--;
					startCol++;
				}
			}
		}
		return result;
	}

	private static boolean isOutOfBounds(int startRow, int startCol, int endRow, int endCol) {
		return startRow < 0 || startCol < 0 ||
				startRow > endRow || startCol > endCol;
	}

	public static void main(String[] args) {
		List<List<Integer>> test = new ArrayList<List<Integer>>();
		test.add(new ArrayList<Integer>(Arrays.asList(1, 3, 4, 10)));
		test.add(new ArrayList<Integer>(Arrays.asList(2, 5, 9, 11)));
		test.add(new ArrayList<Integer>(Arrays.asList(6, 8, 12, 15)));
		test.add(new ArrayList<Integer>(Arrays.asList(7, 13, 14, 16)));
		List<Integer> expected =
				new ArrayList<Integer>(
						Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
		zigzagTraverse(test).equals(expected);
	}
}
