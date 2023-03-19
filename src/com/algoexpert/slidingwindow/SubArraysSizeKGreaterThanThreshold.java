package com.algoexpert.slidingwindow;

public class SubArraysSizeKGreaterThanThreshold {

	public static int numOfSubarrays(int[] arr, int k, int threshold) {
		int left = 0;
		int right = 0;
		int sum = 0;
		int count = 0;

		while (right < arr.length) {
			sum += arr[right];
			if ((right - left + 1) < k) {
				right++;
			} else if ((right - left + 1) == k) {
				if (sum / k >= threshold) {
					count++;
				}
				sum -= arr[left];
				left++;
				right++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] nums = { 2, 2, 2, 2, 5, 5, 5, 8 };
		numOfSubarrays(nums, 3, 4);
	}

}
