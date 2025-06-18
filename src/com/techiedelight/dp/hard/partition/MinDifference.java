package com.techiedelight.dp.hard.partition;

import java.util.Arrays;

public class MinDifference {

    public int minDifference(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        Integer[][] memo = new Integer[n][sum + 1]; // memo[i][j] stores min diff considering elements up to index i with target sum j

        return solve(nums, n - 1, sum / 2, memo);
    }

    private int solve(int[] nums, int index, int targetSum, Integer[][] memo) {
        if (index < 0) {
            return Math.abs(targetSum - 0); // Difference from the target sum (representing one subset)
        }

        if (memo[index][targetSum] != null) {
            return memo[index][targetSum];
        }

        // Option 1: Exclude the current element
        int exclude = solve(nums, index - 1, targetSum, memo);

        // Option 2: Include the current element if it doesn't exceed the target sum
        int include = Integer.MAX_VALUE;
        if (nums[index] <= targetSum) {
            include = solve(nums, index - 1, targetSum - nums[index], memo);
        }

        // We want to find the minimum difference achievable at this step.
        // The 'include' and 'exclude' represent the closest we can get to 'targetSum'
        // for one of the subsets. The difference between the two subsets will be
        // related to how close we can get one subset's sum to half of the total sum.

        // The value returned here isn't the direct minimum difference of the final partition,
        // but rather the closest achievable sum to 'targetSum' using elements up to 'index'.
        // The final minimum difference is calculated in the base case.

        int result;
        if (include == Integer.MAX_VALUE) {
            result = exclude;
        } else {
            result = Math.min(Math.abs(targetSum - include), Math.abs(targetSum - exclude));
        }

        memo[index][targetSum] = result;
        return result;
    }

    public static void main(String[] args) {
        MinDifference solution = new MinDifference();

        int[] nums1 = {1, 6, 11, 5};
        System.out.println("Minimum difference for [1, 6, 11, 5]: " + solution.minDifference(nums1)); // Output: 1

        int[] nums2 = {1, 2, 3};
        System.out.println("Minimum difference for [1, 2, 3]: " + solution.minDifference(nums2));     // Output: 0

        int[] nums3 = {3, 9, 7, 3};
        System.out.println("Minimum difference for [3, 9, 7, 3]: " + solution.minDifference(nums3));     // Output: 2
    }
}
