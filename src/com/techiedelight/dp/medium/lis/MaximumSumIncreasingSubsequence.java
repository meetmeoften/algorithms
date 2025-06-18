package com.techiedelight.dp.medium.lis;

public class MaximumSumIncreasingSubsequence {

    public static int maxSumIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[i] < dp[j] + nums[j]) {
                    dp[i] = dp[j] + nums[i];
                }
            }
        }
        // Find max value in dp
        int maxSum = dp[0];
        for (int val : dp) {
            maxSum = Math.max(maxSum, val);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 101, 2, 3, 100, 4, 5};
        int maxSum = maxSumIS(nums);
        System.out.println("Maximum Sum of Increasing Subsequence: " + maxSum);
    }
}
