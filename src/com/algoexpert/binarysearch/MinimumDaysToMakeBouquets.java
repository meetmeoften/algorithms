package com.algoexpert.binarysearch;

public class MinimumDaysToMakeBouquets {

    // Return the number of maximum bouquets that can be made on day mid.
    private static int getNumOfBouquets(int[] bloomDay, int mid, int k) {
        int numOfBouquets = 0;
        int count = 0;

        for (int i = 0; i < bloomDay.length; i++) {
            // If the flower is bloomed, add to the set. Else reset the count.
            if (bloomDay[i] <= mid) {
                count++;
            } else {
                count = 0;
            }

            if (count == k) {
                numOfBouquets++;
                count = 0;
            }
        }

        return numOfBouquets;
    }

    public static int minDays(int[] bloomDay, int m, int k) {
        int start = 0;
        int end = 0;
        for (int day : bloomDay) {
            end = Math.max(end, day);
        }

        int minDays = -1;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (getNumOfBouquets(bloomDay, mid, k) >= m) {
                minDays = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return minDays;
    }

    public static void main(String[] args) {
        int[] flowers = {3, 6, 7, 11};
        int M = 3;  // Number of bouquets we need
        int k = 1;  // Number of flowers required to make a bouquet

        System.out.println(minDays(flowers, M, k));  // Output: minimum number of days to make M bouquets
    }


}

