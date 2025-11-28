package com.algoexpert.dp;

import java.util.ArrayList;
import java.util.List;

public class BurstBallons2 {

    static int[][] memo;
    static List<Integer> trace = new ArrayList<>();

    static int solve(int[] arr, int i, int j) {
        if (i > j) return 0;

        int max = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
            int val = arr[i - 1] * arr[k] * arr[j + 1];
            int left = solve(arr, i, k - 1);
            int right = solve(arr, k + 1, j);
            int total = val + left + right;

            // Print trace for every call
            System.out.println("Burst at " + k + ": "
                    + "val=" + val + ", left=" + left + ", right=" + right + ", total=" + total);

            max = Math.max(max, total);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1};
        int[] arr = new int[nums.length + 2];
        arr[0] = 1;
        arr[arr.length - 1] = 1;
        for (int i = 0; i < nums.length; ++i) arr[i + 1] = nums[i];
        int ans = solve(arr, 1, nums.length);
        System.out.println("Max coins: " + ans);
    }

}
