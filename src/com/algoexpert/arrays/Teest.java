package com.algoexpert.arrays;

import java.util.Arrays;

public class Teest {

	public static int subArraySum(int[] arr) {
		int max_now = 0, max_end = 0;
		for (int i = 0; i < arr.length; i++) {
			max_now = max_now + arr[i];
			if (max_now < 0) {
				max_now = 0;
			} else if (max_end < max_now) {
				max_end = max_now;
			}
		}
		return max_end;
	}

	public static int getMaxSum(int[] arr) {
		int max = arr[0], start = 0, end = 0, mstart = 0, mend = 0, currmax = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > arr[i] + currmax) {
				currmax = arr[i];
				start = i;
			} else {
				currmax = currmax + arr[i];
			}
			end = i;
			if (currmax > max) {
				max = currmax;
				mstart = start;
				mend = end;
			}
		}
		System.out.println("start:" + mstart + " end:" + mend);
		return max;
	}

	public int maxSubArray(int[] nums) {
		int maxSoFar = nums[0];
		int maxEndHere = nums[0];

		int start = 0;
		int end = 0;

		int tempStart = 0;
		int tempEnd = 0;

		for(int i= 1; i< nums.length; i++) {
			if(nums[i] > nums[i] + maxEndHere  ) {
				maxEndHere = nums[i];
				tempStart = i;
			} else {
				maxEndHere = nums[i] + maxEndHere;
			}
			tempEnd = i;

			if(maxEndHere > maxSoFar) {
				maxSoFar = maxEndHere;
				start = tempStart;
				end = tempEnd;
			}
		}
		int[] arr = new int[] {start, end};
		return maxSoFar;

	}

	double[] maxSubarray(double[] a) {
		double max_so_far = 0;
		double max_ending_here = 0;
		int max_start_index = 0;
		int startIndex = 0;
		int max_end_index = -1;

		for (int i = 0; i < a.length; i++) {
			if (0 > max_ending_here + a[i]) {
				startIndex = i + 1;
				max_ending_here = 0;
			} else {
				max_ending_here += a[i];
			}

			if (max_ending_here > max_so_far) {
				max_so_far = max_ending_here;
				max_start_index = startIndex;
				max_end_index = i;
			}
		}

		if (max_start_index <= max_end_index) {
			return Arrays.copyOfRange(a, max_start_index, max_end_index + 1);
		}

		return null;
	}

	public static void main(String[] args) {
		// int[] array = { -2, -1, -3, 1, 1, 1, -4 };
		int[] array = { 1, 9, 0, 8, -5, 6, -24 };
		System.out.println("Max sum subarray:" + subArraySum(array));

		getMaxSum(array);
	}
}
