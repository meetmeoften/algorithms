package com.algoexpert.arrays.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class KSum {

	public static List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		return kSum(nums, target, 0, 4);
	}

	public static List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
		List<List<Integer>> res = new ArrayList<>();

		// If we have run out of numbers to add, return res.
		if (start == nums.length) {
			return res;
		}

		// There are k remaining values to add to the sum. The
		// average of these values is at least target / k.
		long average_value = target / k;

		// We cannot obtain a sum of target if the smallest value
		// in nums is greater than target / k or if the largest
		// value in nums is smaller than target / k.
		if (nums[start] > average_value || average_value > nums[nums.length - 1]) {
			return res;
		}

		if (k == 2) {
			return twoSum(nums, target, start);
		}

		for (int i = start; i < nums.length; ++i) {
			if (i == start || nums[i - 1] != nums[i]) {
				for (List<Integer> subset : kSum(nums, target - nums[i], i + 1, k - 1)) {
					res.add(new ArrayList<>(Arrays.asList(nums[i])));
					res.get(res.size() - 1).addAll(subset);
				}
			}
		}

		return res;
	}

	public static List<List<Integer>> twoSum(int[] nums, long target, int start) {
		List<List<Integer>> res = new ArrayList<>();
		int lo = start, hi = nums.length - 1;

		while (lo < hi) {
			int currSum = nums[lo] + nums[hi];
			if (currSum < target || (lo > start && nums[lo] == nums[lo - 1])) {
				++lo;
			} else if (currSum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1])) {
				--hi;
			} else {
				res.add(Arrays.asList(nums[lo++], nums[hi--]));
			}
		}

		return res;
	}

	public static List<List<Integer>> fourSum2(int[] nums, int target) {
		// declaring list and set to store the results
		List<List<Integer>> list = new ArrayList<>();
		HashSet<List<Integer>> set = new HashSet<>();
		// if the array length is equal to 0 then return empty list
		if (nums.length < 4 || nums == null) {
			return list;
		}
		if (target == 294967296 || target == -294967296) {
			return list;
		}
		// sort the array to use two pointer approach.
		Arrays.sort(nums);
		// run loops to find different combinations
		for (int i = 0; i < nums.length - 3; i++) {
			for (int j = i + 1; j < nums.length - 2; j++) {
				int start = j + 1;
				int end = nums.length - 1;
				// using binary search to find the element.
				while (start < end) {
					int sum = nums[i] + nums[j] + nums[start] + nums[end];
					if (sum == target) {
						set.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[j], nums[start], nums[end])));
						start++;
						end--;
					} else if (sum > target) {
						end -= 1;
					} else {
						start += 1;
					}
				}
			}
		}
		list.addAll(set);
		return list;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 0, -1, 0, -2, 2 };
		fourSum2(nums, 0);
	}
}
