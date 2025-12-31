package com.algoexpert.dp;

import java.util.HashMap;
import java.util.Map;

public class ArithmeticSlices2 {

    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        long result = 0;
        // dp[i]: map of difference -> count of sequences ending at i
        Map<Long, Integer>[] dp = new HashMap[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long diff = (long) nums[i] - nums[j];

                int countAtJ = dp[j].getOrDefault(diff, 0);
                int countAtI = dp[i].getOrDefault(diff, 0);

                // Extend existing sequences
                dp[i].put(diff, countAtI + countAtJ + 1);

                // Only sequences with length >= 3 are counted
                result += countAtJ;
            }
        }
        return (int) result;
    }

    public static void main(String[] args) {
        ArithmeticSlices2 game = new ArithmeticSlices2();
        game.numberOfArithmeticSlices(new int[] {1, 2, 3, 4});
    }
}
