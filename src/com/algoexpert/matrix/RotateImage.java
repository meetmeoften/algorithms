package com.algoexpert.matrix;

public class RotateImage {

	public void rotate(int[][] matrix) {
		int start = 0;
		int end = matrix.length - 1;

		int[] temp;

		while (start < end) {
			temp = matrix[start];
			matrix[start] = matrix[end];
			matrix[end] = temp;
			start++;
			end--;
		}

		transpose(matrix);

	}

	private void transpose(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i; j < matrix[0].length; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
	}

	public static void main(String[] args) {
		int[][] edges = new int[][] { {1, 2, 3 }, {4, 5, 6 }, { 7, 8, 9 }};
		new RotateImage().rotate(edges);
	}

}
