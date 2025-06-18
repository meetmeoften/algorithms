package com.techiedelight.dp.medium.matrix;

public class MinimumCostToReachToLastCell {

    public static int minCost(int[][] cost) {
        int m = cost.length;
        int n = cost[0].length;

        int[][] dp = new int[m][n];

        dp[0][0] = cost[0][0];

        // Fill first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + cost[0][j];
        }

        // Fill first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + cost[i][0];
        }

        // Fill the rest of the table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + cost[i][j];
            }
        }

        return dp[m - 1][n - 1]; // Minimum cost to reach bottom-right
    }

    public static int findMinCost(int[][] cost, int m, int n) {
        // base case
        if (n == 0 || m == 0) {
            return Integer.MAX_VALUE;
        }
        // if we are in the first cell (0, 0)
        if (m == 1 && n == 1) {
            return cost[0][0];
        }
        // include the current cell's cost in the path and recur to find the minimum
        // of the path from the adjacent left cell and adjacent top cell.
        return Integer.min(findMinCost(cost, m - 1, n), findMinCost(cost, m, n - 1))
                + cost[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] cost = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        int minCost = minCost(cost);
        System.out.println("Minimum cost to reach the bottom-right cell: " + minCost);
    }
}
