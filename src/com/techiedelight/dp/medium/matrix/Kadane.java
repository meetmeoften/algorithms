package com.techiedelight.dp.medium.matrix;

public class Kadane {

    public static void findMaxSumSubarray(int[] arr) {
        // -1, 1, 2

        int start = 0;
        int end = 0;
        int currMax = arr[0];
        int max = arr[0];
        int tempStart = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > currMax + arr[i]) {
                currMax = arr[i];
                tempStart = i;
            } else {
                currMax += arr[i];
            }
            if (currMax > max) {
                max = currMax;
                start = tempStart;
                end = i;
            }
        }

        for (int i = start; i <= end; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        findMaxSumSubarray(new int[]{-1, 1, 2});
    }
}
