package com.techiedelight.dp.hard.matrix;

import java.util.Arrays;

public class MinimumAdjustmentCost {

    int[][] memo;

    public int minAdjustmentCost(int[] A, int target) {
        int n = A.length;
        // Initialize memo table with -1 (indicating uncomputed states)
        memo = new int[n][101];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Try all values from 0 to 100 for the first element
        int minCost = Integer.MAX_VALUE;
        for (int val = 0; val <= 3; val++) {
            minCost = Math.min(minCost, dfs(A, target, 0, val));
        }
        return minCost;
    }

    private int dfs(int[] A, int target, int index, int prevVal) {
        if (index == A.length) return 0;

        // If this state has been computed, return memoized value
        if (memo[index][prevVal] != -1) {
            return memo[index][prevVal];
        }

        int minCost = Integer.MAX_VALUE;
        // Try all valid values for current position
        for (int currVal = 0; currVal <= 3; currVal++) {
            if (index == 0 || Math.abs(currVal - prevVal) <= target) {
                int cost = Math.abs(A[index] - currVal) + dfs(A, target, index + 1, currVal);
                minCost = Math.min(minCost, cost);
            }
        }

        memo[index][prevVal] = minCost;
        return minCost;
    }

    // Example usage
    public static void main(String[] args) {
        MinimumAdjustmentCost solver = new MinimumAdjustmentCost();
        int[] A = {1, 4, 2, 3};
        int target = 1;
        System.out.println("Minimum Adjustment Cost: " + solver.minAdjustmentCost(A, target));
    }


}
