package com.techiedelight.dp.medium.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestAlternateSequence {

    public static int longestAlternatingSubsequence(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] up = new int[n];
        int[] down = new int[n];
        Arrays.fill(up, 1);
        Arrays.fill(down, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    up[i] = Math.max(up[i], down[j] + 1);
                else if (nums[i] < nums[j])
                    down[i] = Math.max(down[i], up[j] + 1);
            }
        }

        int maxUp = Arrays.stream(up).max().getAsInt();
        int maxDown = Arrays.stream(down).max().getAsInt();
        return Math.max(maxUp, maxDown);
    }

    public static List<Integer> getLAS(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();

        int n = nums.length;
        int[] up = new int[n];
        int[] down = new int[n];
        int[] upPrev = new int[n];
        int[] downPrev = new int[n];

        Arrays.fill(up, 1);
        Arrays.fill(down, 1);
        Arrays.fill(upPrev, -1);
        Arrays.fill(downPrev, -1);

        int maxLength = 1;
        int endIndex = 0;
        boolean isUp = true;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && up[i] < down[j] + 1) {
                    up[i] = down[j] + 1;
                    upPrev[i] = j;
                    if (up[i] > maxLength) {
                        maxLength = up[i];
                        endIndex = i;
                        isUp = true;
                    }
                } else if (nums[i] < nums[j] && down[i] < up[j] + 1) {
                    down[i] = up[j] + 1;
                    downPrev[i] = j;
                    if (down[i] > maxLength) {
                        maxLength = down[i];
                        endIndex = i;
                        isUp = false;
                    }
                }
            }
        }

        // Reconstruct the sequence
        List<Integer> sequence = new ArrayList<>();
        while (endIndex != -1) {
            sequence.add(nums[endIndex]);
            endIndex = isUp ? upPrev[endIndex] : downPrev[endIndex];
            isUp = !isUp;
        }

        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 4};
        System.out.println("Longest Alternating Subsequence Length: " + longestAlternatingSubsequence(nums));
    }
}
