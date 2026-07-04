package com.algoexpert.binarysearch;

public class SearchElementInRotatedArray {

    public static void main(String[] args) {
        int arr[] = {16, 19, 3, 5, 8, 10};
        System.out.println("Index of element 5 : " + findElementRotatedSortedArray(arr, 0, arr.length - 1, 8));
        System.out.println("Index of element 5 : " + search(arr,  5));
    }

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            // Left half is sorted
            if (nums[left] <= nums[mid]) {

                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // Right half is sorted
            else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


    public static int findElementRotatedSortedArray(int[] arr, int low, int high, int number) {
        int mid;
        while (low <= high) {
            mid = low + ((high - low) / 2);

            if (arr[mid] == number) {
                return mid;
            }
            if (arr[mid] <= arr[high]) {
                // Right part is sorted
                if (number > arr[mid] && number <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                // Left part is sorted
                if (arr[low] <= number && number < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

}
