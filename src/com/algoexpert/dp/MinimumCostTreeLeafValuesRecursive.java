package com.algoexpert.dp;

public class MinimumCostTreeLeafValuesRecursive {

    public int mctFromLeafValues(int[] arr) {
        return solve(arr, 0, arr.length - 1);
    }

    private int solve(int[] arr, int i, int j) {
        if (i == j) {
            // Single leaf, cost 0
            return 0;
        }

        int minCost = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            int leftCost = solve(arr, i, k);
            int rightCost = solve(arr, k + 1, j);

            int leftMax = findMax(arr, i, k);
            int rightMax = findMax(arr, k + 1, j);

            int totalCost = leftCost + rightCost + (leftMax * rightMax);

            minCost = Math.min(minCost, totalCost);

            // Print steps for visualization
            System.out.println("Partition [" + i + ", " + j + "] at " + k + ": "
                    + "leftCost = " + leftCost + ", rightCost = " + rightCost
                    + ", leftMax = " + leftMax + ", rightMax = " + rightMax
                    + ", totalCost = " + totalCost);
        }

        return minCost;
    }

    private int findMax(int[] arr, int start, int end) {
        int max = arr[start];
        for (int i = start + 1; i <= end; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }

    public static void main(String[] args) {
        MinimumCostTreeLeafValuesRecursive sol = new MinimumCostTreeLeafValuesRecursive();

        int[] arr = {6, 2, 4};
        int minCost = sol.mctFromLeafValues(arr);
        System.out.println("Minimum cost: " + minCost);
    }
}

