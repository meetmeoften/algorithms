package com.algoexpert2.arrays.hard;

import java.util.HashMap;
import java.util.Map;

public class LongestSubarrayWithSumK {

    public static int longestSubarray(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // Case 1: subarray from 0..i has sum k
            if (sum == k) {
                maxLen = i + 1;
            }
            // Case 2: there exists a previous prefix with value (sum - k)
            int need = sum - k;
            if (map.containsKey(need)) {
                int len = i - map.get(need);
                if (len > maxLen) {
                    maxLen = len;
                }
            }

            // Store first occurrence of this prefix sum
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = {1,2, 3};
        int k = 3;
        System.out.println(longestSubarray(arr, k));

        int[] arr1 = {1, -1, 5, -2, 3};
        int k1 = 3;
        System.out.println(longestSubarray(arr1, k1)); // 4  -> [1, -1, 5, -2]

        int[] arr2 = {10, 5, 2, 7, 1, -10};
        int k2 = 15;
        System.out.println(longestSubarray(arr2, k2)); // 6
    }
}