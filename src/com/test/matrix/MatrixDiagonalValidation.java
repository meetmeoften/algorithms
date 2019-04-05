package com.test.matrix;

public class MatrixDiagonalValidation {


	private static void diagonalOrder(int[][] arr, int R, int C) {

		/* through this for loop we choose each element
            of first column as starting point and print
            diagonal starting at it. arr[0][0], arr[1][0]
            ....arr[R-1][0] are all starting points */
		for (int k = R; k > 0; k--)
		{
			System.out.print(arr[k][0] + " ");

			int i = k - 1;    // set row index for next
			// point in diagonal
			int j = 1;       //  set column index for
			// next point in diagonal

			/* Print Diagonally upward */
			while (isValid(i, j, R, C))
			{
				System.out.print(arr[i][j] + " ");
				i--;
				j++;    // move in upright direction
			}
			System.out.println("");
		}

		/* through this for loop we choose each element
               of last row as starting point (except the
               [0][c-1] it has already been processed in
               previous for loop) and print diagonal
               starting at it. arr[R-1][0], arr[R-1][1]....
               arr[R-1][c-1] are all starting points */

		// Note : we start from k = 1 to C-1;
		for (int k = 1; k < C; k++)
		{
			System.out.print(arr[R-1][k] + " ");

			int i = R - 2; // set row index for next
			// point in diagonal
			int j = k + 1; // set column index for
			// next point in diagonal

			/* Print Diagonally upward */
			while (isValid(i, j, R, C))
			{
				System.out.print(arr[i][j] + " ");

				i--;
				j++; // move in upright direction
			}

			System.out.println("");
		}
	}

	public static  boolean isValid(int i, int j, int R, int C)
	{
		if (i < 0 || i >= R || j >= C || j < 0) {
			return false;
		}
		return true;
	}

	// driver program to test above function
	public static void main(String[] args) {
		int arr[][] = {
				{1, 2, 3, 4},
				{9, 10, 11, 12},
				{13, 14, 15, 16},
				{17, 18, 19, 20}, };

		int R = arr.length;
		int C = arr[0].length;

		diagonalOrder(arr, R , C);
		//		printMatrixDiagonally(arr);
	}

	private static void printMatrixDiagonally(int[][] matrix){

		int rowCount = matrix.length;
		int columnCount = matrix[0].length;

		for (int r = 0; r < rowCount; r++) {
			for (int row = r, col = 0; row >= 0 && col < columnCount; row--, col++) {
				System.out.print(matrix[row][col] + " ");
			}
			System.out.println();
		}

		for (int c = 1; c < columnCount; c++) {
			for (int row = rowCount-1, col = c; row >= 0 && col < columnCount; row--, col++) {
				System.out.print(matrix[row][col] + " ");
			}
			System.out.println();
		}

	}
}