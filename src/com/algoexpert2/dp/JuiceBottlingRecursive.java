package com.algoexpert2.dp;

import java.util.*;

public class JuiceBottlingRecursive {

    public static int minCostToBottle(int[] cost, int n) {
        Integer[] memo = new Integer[n + 1];
        int result = helper(cost, n, memo);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static int helper(int[] cost, int remaining, Integer[] memo) {
        // Base case:
        if (remaining == 0) return 0;

        // Memoized answer:
        if (memo[remaining] != null) return memo[remaining];

        int minCost = Integer.MIN_VALUE;

        // Try every bottle size we can fit
        for (int size = 1; size <= remaining; size++) {
            if (size >= cost.length || cost[size] == -1) continue;

            int sub = cost[size] + helper(cost, remaining - size, memo);

            if (sub != Integer.MIN_VALUE) {
                minCost = Math.max(minCost, sub);
            }
        }

        memo[remaining] = minCost;
        return minCost;
    }

    public static void main(String[] args) {
        int[] cost = {0, 3, 5,15, 16};
        int n = 4;

        System.out.println(minCostToBottle(cost, n));  // Output: 9
    }
}

