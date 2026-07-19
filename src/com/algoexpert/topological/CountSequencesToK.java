package com.algoexpert.topological;

import java.util.HashMap;
import java.util.Map;

public class CountSequencesToK {

    private Map<String, Integer> memo = new HashMap<>();

    public int countSequences(int[] nums, long k) {
        return solve(nums, k, 0, 1L, 1L);
    }

    private int solve(int[] nums, long k, int i, long mul, long div) {

        if (i == nums.length) {
            return (mul % div == 0 && mul / div == k) ? 1 : 0;
        }

        String key = i + "#" + mul + "#" + div;
        if (memo.containsKey(key))
            return memo.get(key);

        int ans = 0;

        // Skip
        ans += solve(nums, k, i + 1, mul, div);

        // Multiply
        ans += solve(nums, k, i + 1, mul * nums[i], div);

        // Divide
        ans += solve(nums, k, i + 1, mul, div * nums[i]);

        memo.put(key, ans);
        return ans;
    }

    private int solve2(int[] nums, long k, int i, long mul, long div) {

        long g = gcd(mul, div);
        mul /= g;
        div /= g;

        if (i == nums.length) {
            return (div == 1 && mul == k) ? 1 : 0;
        }

        String key = i + "#" + mul + "#" + div;
        if (memo.containsKey(key))
            return memo.get(key);

        int ans = 0;

        // Don't use nums[i]
        ans += solve(nums, k, i + 1, mul, div);

        // Multiply by nums[i]
        ans += solve(nums, k, i + 1, mul * nums[i], div);

        // Divide by nums[i]
        ans += solve(nums, k, i + 1, mul, div * nums[i]);

        memo.put(key, ans);
        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    static void main(String[] args) {
        CountSequencesToK countSequencesToK = new CountSequencesToK();
        countSequencesToK.countSequences(new int[]{2, 3, 2}, 6);
    }
}
