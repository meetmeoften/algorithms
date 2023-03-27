package com.algoexpert2.binarySearch;

public class SearchInSortedMatrix {

	public static int[] searchInSortedMatrix(int[][] matrix, int target) {
		// Write your code here.
		int row = 0;
		int col = matrix[0].length - 1;

		while (row < matrix.length && col >= 0) {
			int matrixValue = matrix[row][col];
			if (matrixValue > target) {
				col--;
			} else if (matrixValue < target) {
				row++;
			} else {
				return new int[] { row, col };
			}
		}
		return new int[] { -1, -1 };
	}

	public static void main(String[] args) {
		int[][] matrix = {
				{1, 4, 7, 12, 15, 1000},
				{2, 5, 19, 31, 32, 1001},
				{3, 8, 24, 33, 35, 1002},
				{40, 41, 42, 44, 45, 1003},
				{99, 100, 103, 106, 128, 1004},
		};
		searchInSortedMatrix(matrix, 2);
	}

}
