package com.algoexpert.binarysearch;

public class MinimumInRotatedSortedArray {

	public static int findMin(int[] nums) {
		int low = 0, high = nums.length - 1;

		while (low < high) {
			int mid = low + (high - low) / 2;

			if (nums[low] <= nums[mid] && nums[mid] > nums[high]) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return nums[low];
	}


	public static int findMin2(int[] nums) {
		int l = 0;
		int r = nums.length - 1;

		while (l <= r) {
			if (nums[l] <= nums[r]) {
				return nums[l];
			}

			int mid = (l + r) / 2;
			if (nums[mid] >= nums[l]) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}
		return 0;
	}


	public static void main(String[] args) {
		findMin(new int[] {3,4,5,1,2});
		findMin2(new int[] {4,5,6,7,0,1,2});
	}

}
