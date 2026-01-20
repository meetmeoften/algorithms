package com.algoexpert.binarysearch;

import java.util.Arrays;

public class AggressiveCows {

    public static int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls);
        int low = 1;
        int high = stalls[stalls.length - 1] - stalls[0];
        int result = 0;

        while (low <= high) {
            System.out.println(low + " " + high);
            int mid = (low + high) / 2;

            if (canPlaceCows(stalls, k, mid)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    public static boolean canPlaceCows(int[] stalls, int k, int distance) {
        int count = 1; // Place first cow at the first stall
        int lastPosition = stalls[0];

        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPosition >= distance) {
                count++;
                lastPosition = stalls[i];
            }
            if (count == k) return true;
        }

        return false;
    }


    public static void main(String[] args) {
        int[] stalls = {1, 2, 8, 4, 9};
        int cows = 3;
        System.out.println("Largest minimum distance: " + aggressiveCows(stalls, cows));
    }
}
