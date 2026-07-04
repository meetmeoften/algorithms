package com.algoexpert2.dp;

import com.algoexpert.binarysearch.SplitArrayLargestSum;

public class SplitArrayLargestSumDP {

    private int[] nums;
    private int[] prefix;
    private Integer[][] memo;
    private int n;

    public int splitArray(int[] nums, int k) {
        this.nums = nums;
        this.n = nums.length;

        // Prefix sum
        prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        memo = new Integer[n][k + 1];

        return dfs(0, k);
    }

    private int dfs(int index, int k) {
        // Base case: last subarray
        if (k == 1) {
            return prefix[n] - prefix[index];
        }

        // Memo check
        if (memo[index][k] != null) {
            return memo[index][k];
        }

        int minLargestSum = Integer.MAX_VALUE;

        // Try splitting at every possible position
        for (int i = index; i <= n - k; i++) {
            int currentSum = prefix[i + 1] - prefix[index];
            int largest = Math.max(currentSum, dfs(i + 1, k - 1));
            minLargestSum = Math.min(minLargestSum, largest);
        }

        memo[index][k] = minLargestSum;
        return minLargestSum;
    }

    public static void main(String[] args) {
        SplitArrayLargestSumDP splitArrayLargestSum = new SplitArrayLargestSumDP();
        System.out.println(splitArrayLargestSum.splitArray(new int[]{7, 2, 5, 10, 8}, 2));

    }

}
