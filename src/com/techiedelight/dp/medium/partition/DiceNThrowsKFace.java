package com.techiedelight.dp.medium.partition;

import java.util.Arrays;

public class DiceNThrowsKFace {
    static final int MOD = 1_000_000_007;
    static int[][] memo;

    public static int numWays(int n, int k, int target) {
        memo = new int[n + 1][target + 1];
        for (int[] row : memo)
            Arrays.fill(row, -1);
        return dfs(n, k, target);
    }

    private static int dfs(int diceLeft, int faces, int target) {
        if(target < 0) {
            return 0;
        }

        if(diceLeft == 0) {
            return target ==0 ? 1 : 0;
        }

        if(memo[diceLeft][target] != -1) {
            return memo[diceLeft][target];
        }

        int ways = 0;
        for (int face = 1; face <= faces; face++) {
            ways = (ways + dfs(diceLeft -1, faces, target - face)) % MOD;
        }
        memo[diceLeft][target] = ways;
        return ways;
    }

    public static void main(String[] args) {
        int n = 1;
        int k = 2;
        int target = 2;
        System.out.println("Total ways to get sum " + target + ": " + numWays(n, k, target));
    }
}
