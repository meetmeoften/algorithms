package com.algoexpert.matrix;

public class MatrixZeros {

	public void setZeroes(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return;
		}

		boolean isZeroCol= false;
		for(int i= 0; i < matrix.length; i++) {
			if(matrix[i][0] == 0) {
				isZeroCol = true;
			}
		}

		boolean isZeroRow= false;
		for(int i= 0; i < matrix[0].length; i++) {
			if(matrix[0][i] == 0) {
				isZeroRow = true;
			}
		}

		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if(matrix[i][j] == 0) {
					matrix[i][0] = matrix[0][j] = 0;
				}
			}
		}

		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if(matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}



		if (isZeroCol) {
			for (int i = 0; i < matrix.length; i++) {
				matrix[i][0] = 0;
			}
		}

		if (isZeroRow) {
			for (int i = 0; i < matrix[0].length; i++) {
				matrix[0][i] = 0;
			}
		}

	}

	public static void main(String[] args) {
		int[][] edges = new int[][] { {1, 1, 1 }, {1, 0, 1 }, { 1, 1, 1}};
		new MatrixZeros().setZeroes(edges);
	}

}
