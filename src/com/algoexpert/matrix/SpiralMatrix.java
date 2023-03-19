package com.algoexpert.matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

	public static List<Integer> spiralOrder(int[][] array) {
		List<Integer> results = new ArrayList<>();
		if (array.length == 0) {
			return results;
		}
		int startRow = 0;
		int endRow = array.length - 1;

		int startCol = 0;
		int endCol = array[0].length - 1;

		while (startRow <= endRow && startCol <= endCol) {
			for (int col = startCol; col <= endCol; col++) {
				results.add(array[startRow][col]);
			}

			for (int row = startRow + 1; row <= endRow; row++) {
				results.add(array[row][endCol]);
			}

			for (int col = endCol - 1; col >= startCol; col--) {
				if (startRow == endRow) {
					break;
				}
				results.add(array[endRow][col]);
			}

			for (int row = endRow - 1; row > startRow; row--) {
				if (startCol == endCol) {
					break;
				}
				results.add(array[row][startCol]);
			}

			startRow++;
			startCol++;
			endRow--;
			endCol--;
		}

		return results;

	}


	public static void main(String[] args) {
		int matrix[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		spiralOrder(matrix);
	}

}
