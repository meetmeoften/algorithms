package com.algoexpert2.dp;

public class SplitArrayLargestSumDP2 {

    private int[] nums;
    private Integer[][] memo;
    private int n;

    public int splitArray(int[] nums, int k) {
        this.nums = nums;
        this.n = nums.length;
        memo = new Integer[n][k + 1];
        return dfs(0, k);
    }

    private int dfs(int index, int k) {
        // Base case: last subarray
        if (k == 1) {
            return sumFrom(index);
        }

        if (memo[index][k] != null) {
            return memo[index][k];
        }

        int result = splitHelper(index, k, index, 0, Integer.MAX_VALUE);
        memo[index][k] = result;
        return result;
    }

    // Recursively tries all split points (replaces for-loop)
    private int splitHelper(int start, int k, int i, int currentSum, int best) {
        if (i > n - k) {
            return best;
        }

        currentSum += nums[i];
        int next = dfs(i + 1, k - 1);
        int worst = Math.max(currentSum, next);
        best = Math.min(best, worst);

        return splitHelper(start, k, i + 1, currentSum, best);
    }

    // Recursive sum (no loops)
    private int sumFrom(int index) {
        if (index == n) return 0;
        return nums[index] + sumFrom(index + 1);
    }

    public static void main(String[] args) {
        SplitArrayLargestSumDP2 splitArrayLargestSum = new SplitArrayLargestSumDP2();
        System.out.println(splitArrayLargestSum.splitArray(new int[]{7, 2, 5, 10, 8}, 2));

    }

}
