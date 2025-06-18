package com.practice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestFibonacciSubsequence {

    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        // Store array elements in set for O(1) lookup
        Set<Integer> numSet = new HashSet<>();
        for (int num : arr) {
            numSet.add(num);
        }

        int maxLen = 0;

        for (int start = 0; start < n; ++start) {
            for (int next = start + 1; next < n; ++next) {
                int prev = arr[next];
                int curr = arr[start] + arr[next];
                int len = 2;

                while (numSet.contains(curr)) {
                    int temp = curr;
                    curr += prev;
                    prev = temp;
                    maxLen = Math.max(maxLen, ++len);
                }
            }
        }
        return maxLen;
    }

    public int lenLongestFibSubseq2(int[] arr) {
        int maxLen = 0;
        // dp[prev][curr] stores length of Fibonacci sequence ending at indexes prev,curr
        int[][] dp = new int[arr.length][arr.length];

        // Map each value to its index for O(1) lookup
        Map<Integer, Integer> valToIdx = new HashMap<>();

        // Fill dp array
        for (int curr = 0; curr < arr.length; curr++) {
            valToIdx.put(arr[curr], curr);

            for (int prev = 0; prev < curr; prev++) {
                // Find if there exists a previous number to form Fibonacci sequence
                int diff = arr[curr] - arr[prev];
                int prevIdx = valToIdx.getOrDefault(diff, -1);

                // Update dp if valid Fibonacci sequence possible
                // diff < arr[prev] ensures strictly increasing sequence
                if (diff < arr[prev] && prevIdx >= 0) {
                    dp[prev][curr] = dp[prevIdx][prev] + 1;
                } else {
                    dp[prev][curr] = 2;
                }

                maxLen = Math.max(maxLen, dp[prev][curr]);
            }
        }

        // Return 0 if no sequence of length > 2 found
        return maxLen > 2 ? maxLen : 0;
    }

    public static void main(String[] args) {
        LengthOfLongestFibonacciSubsequence len = new LengthOfLongestFibonacciSubsequence();
        len.lenLongestFibSubseq2(new int[]{1, 2, 3, 4, 5});
    }
}
