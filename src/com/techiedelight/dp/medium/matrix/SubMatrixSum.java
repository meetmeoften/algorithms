package com.techiedelight.dp.medium.matrix;

public class SubMatrixSum {


    public static int[][] buildPrefixSumMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] prefix = new int[rows][cols];

        // Fill the prefix sum matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                prefix[i][j] = matrix[i][j] +
                        (i > 0 ? prefix[i - 1][j] : 0) +
                        (j > 0 ? prefix[i][j - 1] : 0) -
                        (i > 0 && j > 0 ? prefix[i - 1][j - 1] : 0);
            }
        }

        return prefix;
    }

    // Function to get the sum of a submatrix (r1, c1) to (r2, c2)
    public static int getSubmatrixSum(int[][] prefix, int r1, int c1, int r2, int c2) {
        int sum = prefix[r2][c2];

        if (c1 > 0) sum -= prefix[r2][c1 - 1];
        if (r1 > 0) sum -= prefix[r1 - 1][c2];
        if (r1 > 0 && c1 > 0) sum += prefix[r1 - 1][c1 - 1];

        return sum;
    }

    // -----------

    static int[][] buildPrefixSum2(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] prefix = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                prefix[i][j] = mat[i - 1][j - 1]
                        + prefix[i - 1][j]
                        + prefix[i][j - 1]
                        - prefix[i - 1][j - 1];

        return prefix;
    }

    // Get sum of submatrix (r1,c1) to (r2,c2)
    static int getSubmatrixSum2(int[][] prefix, int r1, int c1, int r2, int c2) {
        return prefix[r2 + 1][c2 + 1]
                - prefix[r1][c2 + 1]
                - prefix[r2 + 1][c1]
                + prefix[r1][c1];
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        // Build the prefix sum matrix
        int[][] prefix = buildPrefixSumMatrix(matrix);

        // Example: Sum of the submatrix from (1, 1) to (2, 2)
        int sum = getSubmatrixSum(prefix, 1, 1, 2, 2);
        System.out.println("Sum of submatrix from (1,1) to (2,2): " + sum);
    }


}
