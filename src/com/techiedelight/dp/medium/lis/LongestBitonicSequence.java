package com.techiedelight.dp.medium.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LongestBitonicSequence {


    public static int longestBitonicSubsequence(int[] nums) {

        int n = nums.length;

        int[] lis = new int[n];
        int[] lds = new int[n];
        int[] lisPrev = new int[n];  // For backtracking LIS
        int[] ldsNext = new int[n];  // For backtracking LDS

        Arrays.fill(lis, 1);
        Arrays.fill(lds, 1);
        Arrays.fill(lisPrev, -1);
        Arrays.fill(ldsNext, -1);

        // Compute LIS ending at each index
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && lis[j] + 1 > lis[i]) {
                    lis[i] = lis[j] + 1;
                    lisPrev[i] = j;
                }
            }
        }

        // Compute LDS starting at each index
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[j] < nums[i] && lds[j] + 1 > lds[i]) {
                    lds[i] = lds[j] + 1;
                    ldsNext[i] = j;
                }
            }
        }

        // Find peak index with max LIS + LDS - 1
        int maxLen = 0, peak = 0;
        for (int i = 0; i < n; i++) {
            int bitonicLen = lis[i] + lds[i] - 1;
            if (bitonicLen > maxLen) {
                maxLen = bitonicLen;
                peak = i;
            }
        }

        // Reconstruct increasing part
        LinkedList<Integer> increasing = new LinkedList<>();
        int curr = peak;
        while (curr != -1) {
            increasing.addFirst(nums[curr]);
            curr = lisPrev[curr];
        }

        // Reconstruct decreasing part (excluding the peak since already added)
        List<Integer> decreasing = new ArrayList<>();
        curr = ldsNext[peak];
        while (curr != -1) {
            decreasing.add(nums[curr]);
            curr = ldsNext[curr];
        }

        // Combine
        increasing.addAll(decreasing);
        System.out.println("Longest Bitonic Subsequence: " + increasing);
        System.out.println("Length: " + maxLen);

        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {1, 11, 2, 10, 4, 5, 2, 1};
        int result = longestBitonicSubsequence(nums);
        System.out.println("Length of Longest Bitonic Subsequence: " + result);
    }

}
