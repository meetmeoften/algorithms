package com.neetcode.twopointers;

public class RotateArray {

	public static void rotate(int[] nums, int k) {
		int n = nums.length;
		k = k % n;
		flip(nums, 0, n - k - 1);
		flip(nums, n - k, n - 1);
		flip(nums, 0, n - 1);

	}

	public static void flip(int[] arr, int start, int end) {
		while (start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
		rotate(nums, 3);
	}

}
