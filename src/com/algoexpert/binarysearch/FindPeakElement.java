package com.algoexpert.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class FindPeakElement {

    public static int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static List<Integer> findAllPeakElements(int[] nums) {
        List<Integer> peaks = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            boolean leftOK = (i == 0) || (nums[i] >= nums[i - 1]);
            boolean rightOK = (i == nums.length - 1) || (nums[i] >= nums[i + 1]);

            if (leftOK && rightOK) {
                peaks.add(i); // Add index of the peak
            }
        }

        return peaks;
    }

    public static int findPeakElement2(int[] array) {
        if (array.length == 1) {
            return 0;
        }
        int i = 1;
        int index = 0;
        int max = array[0];
        while (i < array.length - 1) {
            boolean isPeak = array[i] > array[i - 1] && array[i] > array[i + 1] && array[i] > max;
            if (isPeak) {
                index = i;
                max = array[i];
            }
            i++;
        }

        if (array[array.length - 1] > max) {
            return array.length - 1;
        }

        return index;

    }

    public static void main(String[] args) {
        int nums[] = {1, 7, 1, 3, 5, 6, 4};
        findPeakElement(nums);
        findAllPeakElements(nums);
        findPeakElement2(nums);
    }

}
