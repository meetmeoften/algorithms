package com.techiedelight.dp.hard.matrix;

import java.util.Arrays;

public class MaxPointsMatrix {
    int[][] dp;
    int[][] matrix;
    int rows, cols;

    public int collectMaxPoints(int[][] mat) {
        this.matrix = mat;
        this.rows = mat.length;
        this.cols = mat[0].length;
        this.dp = new int[rows][cols];

        for (int[] row : dp) Arrays.fill(row, -1);

        int result = dfs(0, 0);
        return result < 0 ? 0 : result; // Return 0 if no valid path
    }

    private int dfs(int r, int c) {
        // Out of bounds or unsafe cell
        if (r < 0 || r >= rows || c < 0 || c >= cols || matrix[r][c] == -1) return Integer.MIN_VALUE;

        if (dp[r][c] != -1) return dp[r][c];

        int current = matrix[r][c] == 1 ? 1 : 0;
        int max = Integer.MIN_VALUE;

        // Determine allowed directions based on row parity
        int[][] directions = (r % 2 == 0) ?
                new int[][]{{0, 1}, {1, 0}} :  // even row → right, down
                new int[][]{{0, -1}, {1, 0}}; // odd row → left, down

        for (int[] dir : directions) {
            int nextR = r + dir[0];
            int nextC = c + dir[1];
            max = Math.max(max, dfs(nextR, nextC));
        }

        dp[r][c] = (max == Integer.MIN_VALUE) ? current : current + max;
        return dp[r][c];
    }

    public static boolean isSafe(int[][] mat, int i, int j) {
        return !(i < 0 || i >= mat.length || j < 0 || j >= mat[0].length ||
                mat[i][j] == -1);
    }

    // Function to collect the maximum number of ones starting from
    // cell mat[i][j]
    public static int findMaximum(int[][] mat, int i, int j) {
        if (mat == null || mat.length == 0) {
            return 0;
        }

        // return if cell (i, j) is invalid or unsafe to visit
        if (!isSafe(mat, i, j)) {
            return 0;
        }

        // if the row is odd, we can go left or down
        if ((i & 1) == 1) {
            return mat[i][j] + Integer.max(findMaximum(mat, i, j - 1),
                    findMaximum(mat, i + 1, j));
        }

        // if the row is even, we can go right or down
        else {
            return mat[i][j] + Integer.max(findMaximum(mat, i, j + 1),
                    findMaximum(mat, i + 1, j));
        }
    }

    public static void main(String[] args) {
        MaxPointsMatrix solver = new MaxPointsMatrix();

        int[][] mat =
                {
                        {1, 1, -1, 1, 1},
                        {1, 0, 0, -1, 1},
                        {1, 1, 1, 1, -1},
                        {-1, -1, 1, 1, 1},
                        {1, 1, -1, -1, 1}
                };

        System.out.println("Maximum points collected: " + solver.collectMaxPoints(mat));
    }
}


