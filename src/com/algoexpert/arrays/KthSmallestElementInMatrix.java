package com.algoexpert.arrays;

public class KthSmallestElementInMatrix {

	public static int kthSmallest(int[][] matrix, int k) {
		int low = matrix[0][0], high = matrix[matrix.length - 1][matrix[0].length - 1];

		while (low <= high) {
			int mid = low + (high - low) / 2;

			if (countLessThan(matrix, mid) >= k) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return low;
	}

	private static int countLessThan(int[][] matrix, int target) {
		int row = matrix.length - 1, col = 0, result = 0;

		while (row >= 0 && col < matrix[0].length) {
			if (matrix[row][col] <= target) {
				result += row + 1;
				++col;
			} else {
				--row;
			}
		}

		return result;
	}


	public static void main(String[] args) {
		int[][] matrix = {{1,5,9},{10,11,13},{12,13,15}};
		int k = 8;

		kthSmallest(matrix, k);
	}

}
