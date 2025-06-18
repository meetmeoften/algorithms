package com.algoexpert.binarysearch;

public class LowerBound {

    public static int lowerBound(int[] arr, int n, int x) {
        for (int i = 0; i < n; i++) {
            if (arr[i] >= x) {
                // lower bound found:
                return i;
            }
        }
        return n;
    }

    public static int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) {
                right = mid;  // mid might be the lower bound
            } else {
                left = mid + 1;  // discard mid and everything before it
            }
        }

        return left;
    }

    public static int lowerBoundGreater(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] > target) {
                right = mid;  // keep mid, it's a potential answer
            } else {
                left = mid + 1;  // skip mid, it's ≤ target
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 4, 5, 7};
        int target = 4;
        int result = lowerBound(arr, target);
        System.out.println("First element >= " + target + " is at index: " + result + ", value: " + arr[result]);
        result = lowerBoundGreater(arr, target);
        System.out.println("First element > " + target + " is at index: " + result + ", value: " + arr[result]);
    }
}
