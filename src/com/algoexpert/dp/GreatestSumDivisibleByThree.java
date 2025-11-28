package com.algoexpert.dp;

public class GreatestSumDivisibleByThree {

    public int maxSumDivThree(int[] nums) {
        int[] dp = new int[3];
        dp[1] = dp[2] = Integer.MIN_VALUE;  // impossible states initially

        for (int num : nums) {
            int[] next = dp.clone();
            for (int r = 0; r < 3; r++) {
                int newR = (r + num) % 3;
                next[newR] = Math.max(next[newR], dp[r] + num);
            }
            dp = next;
        }

        return dp[0];
    }

    public int maxSumDivK(int[] nums, int k) {
        return helper(nums, 0, 0, k);
    }

    private int helper(int[] nums, int i, int sum, int k) {
        if (i == nums.length) {
            return sum % k == 0 ? sum : 0;
        }
        // Include this number
        int take = helper(nums, i + 1, sum + nums[i], k);
        // Exclude this number
        int skip = helper(nums, i + 1, sum, k);
        return Math.max(take, skip);
    }

    public static void main(String[] args) {
        GreatestSumDivisibleByThree greatestSumDivisibleByThree = new GreatestSumDivisibleByThree();
        greatestSumDivisibleByThree.maxSumDivThree(new int[]{3, 6, 5, 1, 8});
        System.out.println(greatestSumDivisibleByThree.maxSumDivK(new int[]{3, 6, 5, 1, 8}, 3));
    }
}
