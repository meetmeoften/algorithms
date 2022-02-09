package com.algoexpert.dp;

public class MaxSumSubMatrix {

	public int maximumSumSubmatrix(int[][] matrix, int size) {
		int[][] sums = createSumMatrix(matrix);
		int maxSubMatrixSum = Integer.MIN_VALUE;

		for(int row= size-1; row< matrix.length;row++) {
			for(int col= size-1; col< matrix[0].length; col++) {
				int total = sums[row][col];

				boolean touchesTopBorder = row - size < 0;
				if(!touchesTopBorder) {
					total = total- sums[row -size][col];
				}

				boolean touchesLeftBorder = col-size <0;
				if(!touchesLeftBorder) {
					total = total- sums[row][col-size];
				}

				boolean touchesTopOrLeftBorder = (!touchesLeftBorder && !touchesTopBorder);
				if(touchesTopOrLeftBorder) {
					total = total + sums[row-size][col-size];
				}
				maxSubMatrixSum = Math.max(maxSubMatrixSum, total);
			}
		}
		return 0;
	}

	private int[][] createSumMatrix(int[][] matrix) {
		int[][] sums = new int[matrix.length][matrix[0].length];
		sums[0][0] = matrix[0][0];

		for(int row= 1; row < matrix.length; row++) {
			sums[row][0]= sums[row-1][0] + matrix[row][0];
		}

		for(int col=1; col< matrix[0].length; col++) {
			sums[0][col] = sums[0][col-1] + matrix[0][col];
		}

		for(int  row = 1; row< matrix.length; row++) {
			for(int col = 1; col< matrix[0].length; col++) {
				sums[row][col] = matrix[row][col] +
						sums[row-1][col] +
						sums[row][col-1]-
						sums[row-1][col-1];

			}
		}
		return sums;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] {{5, 3, -1, 5}, {-7, 3, 7, 4}, {12, 8, 0, 0}, {1, -8, -8, 2}};
		int size = 2;
		int expected = 18;
		var actual = new MaxSumSubMatrix().maximumSumSubmatrix(matrix, size);
	}

}
