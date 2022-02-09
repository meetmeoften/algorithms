package com.test.general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

	public static List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		return kSum(nums, target, 0, 4);
	}

	public static List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
		List<List<Integer>> res = new ArrayList<>();
		if (start == nums.length || nums[start] * k > target || target > nums[nums.length - 1] * k) {
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

	public static List<List<Integer>> twoSum(int[] nums, int target, int start) {
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

	public static void main(String[] args) {
		int arr[] = new int[] {7, 6, 4, -1, 1, 2};
		System.out.println(fourSum(arr, 16));
	}

}
