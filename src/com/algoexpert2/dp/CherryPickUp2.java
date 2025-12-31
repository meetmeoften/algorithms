package com.algoexpert2.dp;

public class CherryPickUp2 {

    int[][] grid;
    int m, n;
    Integer[][][] memo;

    public int cherryPickup(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        this.memo = new Integer[m][n][n];
        return dfs(0, 0, n - 1);
    }

    private int dfs(int row, int col1, int col2) {
        if (col1 < 0 || col1 >= n || col2 < 0 || col2 >= n) return Integer.MIN_VALUE;
        if (row == m) return 0;

        if (memo[row][col1][col2] != null) return memo[row][col1][col2];

        int cherries = grid[row][col1];
        if (col1 != col2) cherries += grid[row][col2];

        int maxNext = Integer.MIN_VALUE;
        for (int newCol1 = col1 - 1; newCol1 <= col1 + 1; newCol1++) {
            for (int newCol2 = col2 - 1; newCol2 <= col2 + 1; newCol2++) {
                maxNext = Math.max(maxNext, dfs(row + 1, newCol1, newCol2));
            }
        }

        memo[row][col1][col2] = cherries + maxNext;
        return memo[row][col1][col2];
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 1, -1}, {1, 0, -1}, {1, 1, 1}};
        CherryPickUp2 cp = new CherryPickUp2();
        System.out.println(cp.cherryPickup(grid));
    }

}
