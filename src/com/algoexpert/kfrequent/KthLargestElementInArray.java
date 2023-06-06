package com.algoexpert.kfrequent;

public class KthLargestElementInArray {

	public int findKthLargest(int[] nums, int k) {
		k = nums.length - k;
		return quickSelect(nums, 0, nums.length - 1, k);
	}

	private int quickSelect(int[] nums, int low, int high, int k) {
		int idx = low, pivot = high;

		for (int i = low; i < high; i++) {
			if (nums[i] <= nums[pivot]) {
				swap(nums, i, idx);
				++idx;
			}
		}

		swap(nums, idx, pivot);

		if (idx == k) {
			return nums[idx];
		} else if (idx < k) {
			return quickSelect(nums, idx + 1, high, k);
		} else {
			return quickSelect(nums, low, idx - 1, k);
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void main(String[] args) {
		int[] nums = { 2, 4, 1, 5, 6, 3 };
		int k = 2;
		KthLargestElementInArray kth = new KthLargestElementInArray();
		kth.findKthLargest(nums, k);

	}

}
