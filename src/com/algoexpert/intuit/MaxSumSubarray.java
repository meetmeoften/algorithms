package com.algoexpert.intuit;

import java.util.ArrayList;
import java.util.Arrays;

public class MaxSumSubarray {

	static ArrayList<Integer> findSubarray(int a[], int n) {
		// code here
		int s = 0;
		int end = 0;
		int start = 0;
		long sum = 0;
		long max = 0;

		for (int i = 0; i < n; i++) {
			sum = sum + a[i];
			if (sum > max) {
				max = sum;
				start = s;
				end = i;
			}

			if (a[i] == 0 && sum == max) {
				end++;
			}

			if (a[i] < 0) {
				sum = 0;
				s = i + 1;
			}
		}
		// System.out.println(count);
		ArrayList<Integer> list = new ArrayList<>();
		if (max > 0) {
			for (int i = start; i <= end; i++) {
				list.add(a[i]);
			}
			return list;
		}
		list.add(-1);
		return list;
	}

	public static int[] maxSubArrayWithIndices(int[] nums) {
		int maxSum = nums[0], currentSum = nums[0];
		int start = 0, end = 0, tempStart = 0;

		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > currentSum + nums[i]) {
				currentSum = nums[i];
				tempStart = i;
			} else {
				currentSum += nums[i];
			}

			if (currentSum > maxSum) {
				maxSum = currentSum;
				start = tempStart;
				end = i;
			}
		}

		int[] result =  Arrays.copyOfRange(nums, start, end + 1);
		return result;
	}


	public static void main(String[] args) {
		int[] arr = new int[]{1, 2, 5, 0, -7, 7, 3};
		System.out.println(findSubarray(arr, arr.length));
		System.out.println(maxSubArrayWithIndices(arr));
	}

}
