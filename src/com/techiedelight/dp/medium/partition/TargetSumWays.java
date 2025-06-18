package com.techiedelight.dp.medium.partition;

import java.util.HashMap;
import java.util.Map;

public class TargetSumWays {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println("Total ways: " + findTargetSumWays(nums, target));
    }

    public static int findTargetSumWays(int[] nums, int target) {
        Map<String, Integer> memo = new HashMap<>();
        return dfs(nums, 0, 0, target, memo);
    }

    // DFS with memoization
    private static int dfs(int[] nums, int index, int currentSum, int target, Map<String, Integer> memo) {
        String key = index + "," + currentSum;
        if (memo.containsKey(key)) return memo.get(key);

        if (index == nums.length) {
            return currentSum == target ? 1 : 0;
        }

        int add = dfs(nums, index + 1, currentSum + nums[index], target, memo);
        int subtract = dfs(nums, index + 1, currentSum - nums[index], target, memo);

        memo.put(key, add + subtract);
        return add + subtract;
    }
}

