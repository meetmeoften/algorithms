package com.algoexpert.binarysearch;

public class SmallestDivisor {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int limit = 8;
        int ans = smallestDivisor(arr, limit);
        System.out.println("The minimum divisor is: " + ans);
    }

    private static int smallestDivisor(int[] arr, int limit) {
        int left = 1;
        int right = 1;

        for(int val: arr) {
            right = Math.max(right, val);
        }

        while(left < right) {
            int mid = left + (right - left)/2;

            int total =0;
            for(int val : arr) {
                total +=  Math.ceil((double)(val)/(double)(mid));
            }
            boolean possible = total < limit;
            if(possible) {
                right = mid;
            } else {
                left = mid +1;
            }
        }

        return left;
    }
}
