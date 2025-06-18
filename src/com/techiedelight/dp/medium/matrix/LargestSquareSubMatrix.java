package com.techiedelight.dp.medium.matrix;

public class LargestSquareSubMatrix {

    public static int largestSquareSubmatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = new int[rows][cols];
        int maxSize = 0;

        // Initialize first row and column
        for (int i = 0; i < rows; i++) {
            dp[i][0] = matrix[i][0];
            maxSize = Math.max(maxSize, dp[i][0]);
        }
        for (int j = 0; j < cols; j++) {
            dp[0][j] = matrix[0][j];
            maxSize = Math.max(maxSize, dp[0][j]);
        }

        // Fill the rest of the dp table
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1]));
                    maxSize = Math.max(maxSize, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return maxSize;
    }

    public static void main(String[] args) {
//        int[][] matrix = {
//                {0, 1, 1, 0, 1},
//                {1, 1, 1, 1, 0},
//                {0, 1, 1, 1, 0},
//                {1, 1, 1, 1, 0},
//                {1, 1, 1, 1, 1},
//                {0, 0, 0, 0, 0}
//        };

        int[][] matrix = {
                {0, 1, 1},
                {1, 1, 1},
                {0, 1, 1}
        };

        int size = largestSquareSubmatrix(matrix);
        System.out.println("Largest square submatrix of 1's has size: " + size);
    }

}
