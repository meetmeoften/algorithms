package com.algoexpert.arrays;

public class ZeroTransformationArray {

    public static boolean isZeroArray(int[] nums, int[][] queries) {
        int[] arr = new int[nums.length + 1];
        for (int[] query : queries) {
            int left = query[0];
            int right = query[1];
            arr[left] += 1;
            arr[right + 1] -= 1;
        }

        int[] operations = new int[arr.length];
        int curr = 0;

        for (int i = 0; i < arr.length; i++) {
            curr += arr[i];
            operations[i] = curr;
        }

        for (int i = 0; i < nums.length; i++) {
            if (operations[i] < nums[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] queries = new int[][]{{1, 3}, {0, 2}};
        isZeroArray(new int[]{4, 3, 2, 1}, queries);
    }

}
