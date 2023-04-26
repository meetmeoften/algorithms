package com.neetcode.slidingwindow;

public class MinimumSizeSubArraySum {

	public static int minSubArrayLen(int s, int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}

		int left = 0;
		int right = 0;
		int sum = 0;
		int min = Integer.MAX_VALUE;

		while (right < a.length) {
			sum += a[right];
			while (sum >= s) {
				min = Math.min(min, right - left);
				sum -= a[left];
				left++;
			}
			right++;
		}

		return min == Integer.MAX_VALUE ? 0 : min;

	}

	public static void main(String[] args) {
		int[] nums = { 2, 3, 1, 2, 4, 3 };
		minSubArrayLen(7, nums);
	}

}
