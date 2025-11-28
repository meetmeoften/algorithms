package com.algoexpert.dp;

public class StoneGame2 {
    int[] sum; // Suffix sum array for quick stone count
    int[][] dp; // DP memoization table

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        sum = new int[n + 1];
        dp = new int[n][n + 1];

        // Compute suffix sum for quick lookup
        for (int i = n - 1; i >= 0; i--) {
            sum[i] = piles[i] + sum[i + 1];
        }

        return helper(0, 1, n, piles);
    }

    private int helper(int i, int M, int n, int[] piles) {
        if (i >= n) return 0;
        //if (dp[i][M] != 0) return dp[i][M]; // Memoization

        int maxStones = 0;
        // Try taking x piles, where 1 <= x <= 2*M
        for (int x = 1; x <= 2 * M && i + x <= n; x++) {
            int opponent = helper(i + x, Math.max(M, x), n, piles);
            maxStones = Math.max(maxStones, sum[i] - opponent);
        }
        dp[i][M] = maxStones;
        return maxStones;
    }

    public static void main(String[] args) {
        StoneGame2 stoneGame2 = new StoneGame2();
        stoneGame2.stoneGameII(new int[] {2, 7, 9, 4, 4, 4});
    }
}
