package com.techiedelight.dp.medium.partition;

import java.util.HashMap;
import java.util.Map;

public class MaxSumNoAdjacent {


    public static int maxSum(int[] nums) {
        Map<Integer, Integer> memo = new HashMap<>();
        return dfs(nums, nums.length - 1);
    }

    public static int dfs(int[] arr, int ind) {
        if (ind < 0) {
            return 0;
        }

        int second = arr[ind] + dfs(arr, ind - 2);
        int first = dfs(arr, ind - 1);

        return Math.max(first, second);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println("Max sum (no adjacent): " + maxSum(nums));
    }

}
