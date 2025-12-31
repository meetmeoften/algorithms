package com.algoexpert2.dp;

public class CherryPickUp1 {

    int[][] grid;
    int n;
    Integer[][][] memo;

    public int cherryPickup(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        this.memo = new Integer[n][n][n];
        return Math.max(0, dfs(0, 0, 0));
    }

    private int dfs(int r1, int c1, int r2) {
        int c2 = r1 + c1 - r2;
        if (r1 >= n || c1 >= n || r2 >= n || c2 >= n ||
                grid[r1][c1] == -1 || grid[r2][c2] == -1)
            return Integer.MIN_VALUE;

        if (r1 == n - 1 && c1 == n - 1) return grid[r1][c1];

        if (memo[r1][c1][r2] != null) return memo[r1][c1][r2];

        int cherries = grid[r1][c1];
        if (r1 != r2) cherries += grid[r2][c2];

        int maxNext = Math.max(
                Math.max(dfs(r1 + 1, c1, r2 + 1), dfs(r1, c1 + 1, r2)),
                Math.max(dfs(r1 + 1, c1, r2), dfs(r1, c1 + 1, r2 + 1))
        );

        memo[r1][c1][r2] = cherries + maxNext;
        return memo[r1][c1][r2];
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 1, -1}, {1, 0, -1}, {1, 1, 1}};
        CherryPickUp1 cp = new CherryPickUp1();
        System.out.println(cp.cherryPickup(grid));
    }
}
