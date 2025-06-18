package com.techiedelight.dp.hard.matrix;

public class MaxCoinCollection {

    // Function to check valid positions
    public static boolean isValid(int row, int col1, int col2, int M, int N) {
        return row < M && col1 >= 0 && col1 < N && col2 >= 0 && col2 < N;
    }

    // Recursive function with memoization
    public static int getMaxCoins(int[][] mat, int row, int col1, int col2, Integer[][][] dp) {
        int M = mat.length;
        int N = mat[0].length;

        // Invalid state
        if (!isValid(row, col1, col2, M, N)) {
            return Integer.MIN_VALUE;
        }

        // Memoized result
        if (dp[row][col1][col2] != null) {
            return dp[row][col1][col2];
        }

        // Base case — last row
        if (row == M - 1) {
            if (col1 == 0 && col2 == N - 1) {
                return (col1 == col2) ? mat[row][col1] : mat[row][col1] + mat[row][col2];
            }
            return Integer.MIN_VALUE;
        }

        // Explore all 9 moves explicitly
        int max = Integer.MIN_VALUE;
        max = Math.max(max, getMaxCoins(mat, row + 1, col1 - 1, col2 - 1, dp));
        max = Math.max(max, getMaxCoins(mat, row + 1, col1 - 1, col2, dp));
        max = Math.max(max, getMaxCoins(mat, row + 1, col1 - 1, col2 + 1, dp));
        max = Math.max(max, getMaxCoins(mat, row + 1, col1, col2 - 1, dp));
        max = Math.max(max, getMaxCoins(mat, row + 1, col1, col2, dp));
        max = Math.max(max, getMaxCoins(mat, row + 1, col1, col2 + 1, dp));
        max = Math.max(max, getMaxCoins(mat, row + 1, col1 + 1, col2 - 1, dp));
        max = Math.max(max, getMaxCoins(mat, row + 1, col1 + 1, col2, dp));
        max = Math.max(max, getMaxCoins(mat, row + 1, col1 + 1, col2 + 1, dp));

        // Add current coins
        int coins = (col1 == col2) ? mat[row][col1] : mat[row][col1] + mat[row][col2];
        dp[row][col1][col2] = coins + max;

        return dp[row][col1][col2];
    }

    public static int getMaxCoins(int[][] mat) {
        if (mat == null || mat.length == 0) return 0;

        int M = mat.length;
        int N = mat[0].length;

        Integer[][][] dp = new Integer[M][N][N];
        return getMaxCoins(mat, 0, 0, N - 1, dp);  // start from (0, 0) and (0, N-1)
    }

    public static void main(String[] args) {
        int[][] mat =
                {
                        { 0, 2, 4, 1 },
                        { 4, 8, 3, 7 },
                        { 2, 3, 6, 2 },
                        { 9, 7, 8, 3 },
                        { 1, 5, 9, 4 }
                };
        System.out.println("The maximum coins collected is " + getMaxCoins(mat));
    }
}
