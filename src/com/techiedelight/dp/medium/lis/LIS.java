package com.techiedelight.dp.medium.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LIS {

    public static List<Integer> findLIS(int[] nums) {
        if(nums.length == 0) return new ArrayList<>();

        int n= nums.length;
        int[] lengths = new int[n];
        int[] sequences = new int[n];

        Arrays.fill(lengths, 1);
        Arrays.fill(sequences, -1);

        int maxLen =1;
        int lastIndex = 0;

        for(int i=1; i < n; i++) {
            for(int j=0; j < i; j++) {
                if(nums[i] >  nums[j] && lengths[i] < lengths[j] + 1 ) {
                    lengths[i] = lengths[j] + 1;
                    sequences[i] = j;
                }
            }

            if (lengths[i] > maxLen) {
                maxLen = lengths[i];
                lastIndex = i;
            }
        }
        // Reconstruct LIS
        List<Integer> lis = new ArrayList<>();
        int index = lastIndex;
        while (index != -1) {
            lis.add(0, nums[index]);
            index = sequences[index];
        }
        return lis;
    }

    public static void main(String[] args) {
        List<Integer> lis = findLIS(new int[]{1, 0, 3, 4});

        System.out.println("LIS Length: " + lis.size());
        System.out.println("LIS Sequence: " + lis);
    }
}
