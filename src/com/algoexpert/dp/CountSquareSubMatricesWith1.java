package com.algoexpert.dp;

public class CountSquareSubMatricesWith1 {

    private int[][] matrix;
    private int[][] memo;
    private int m, n;

    public int countSquares(int[][] matrix) {
        this.matrix = matrix;
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.memo = new int[m][n];

        int total = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += dfs(i, j);
            }
        }

        return total;
    }

    private int dfs(int row, int col) {
        // Out of bounds
        if (row >= m || col >= n) {
            return 0;
        }

        // Cell is 0
        if (matrix[row][col] == 0) {
            return 0;
        }

        // Already computed
        if (memo[row][col] != 0) {
            return memo[row][col];
        }

        memo[row][col] = 1 + Math.min(
                dfs(row + 1, col),
                Math.min(dfs(row, col + 1), dfs(row + 1, col + 1))
        );

        return memo[row][col];
    }

    public static void main(String[] args) {
        CountSquareSubMatricesWith1 game = new CountSquareSubMatricesWith1();
        //game.countSquares(new int[][]{{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}});
        game.countSquares(new int[][]{{1, 0, 1}, {1, 1, 0}, {1, 1, 0}});
    }

}
