package com.algoexpert.dp;

public class StoneGame3 {

    Integer[] memo;
    int[] stones;
    int n;

    public String stoneGameIII(int[] stoneValue) {
        this.stones = stoneValue;
        this.n = stoneValue.length;
        this.memo = new Integer[n];

        int diff = dfs(0);

        if (diff > 0) return "Alice";
        if (diff < 0) return "Bob";
        return "Tie";
    }

    private int dfs(int i) {
        if (i >= n) return 0;
        if (memo[i] != null) return memo[i];

        int best = Integer.MIN_VALUE;
        int sum = 0;

        for (int k = 0; k < 3 && i + k < n; k++) {
            sum += stones[i + k];
            best = Math.max(best, sum - dfs(i + k + 1));
        }

        memo[i] = best;
        return best;
    }

}
