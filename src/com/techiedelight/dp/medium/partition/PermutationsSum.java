package com.techiedelight.dp.medium.partition;

import java.util.HashMap;
import java.util.Map;

public class PermutationsSum {


    public static int countPermutations(int[] nums, int target) {
        Map<Integer, Integer> memo = new HashMap<>();
        return dfs(nums, target, memo);
    }

    private static int dfs(int[] nums, int target, Map<Integer, Integer> memo) {
        if (target == 0) {
            return 1;
        }
        if (target < 0) {
            return 0;
        }
        if (memo.containsKey(target)) return memo.get(target);

        int count = 0;
        for (int num : nums) {
            count += dfs(nums, target - num, memo);
        }
        memo.put(target, count);
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2};
        int target = 2;
        System.out.println("Total permutations: " + countPermutations(nums, target));
    }

    public static int countPermutations2(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1; // one way to reach 0

        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
