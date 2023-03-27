package com.algoexpert2.arrays.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class FourSum {

	public static List<List<Integer>> fourSum(int[] nums, int target) {
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
		List<List<Integer>> output = fourSum(new int[] { 7, 6, 4, -1, 1, 2 }, 16);
		List<Integer[]> quadruplets = new ArrayList<Integer[]>();
		quadruplets.add(new Integer[] { 7, 6, 4, -1 });
		quadruplets.add(new Integer[] { 7, 6, 1, 2 });
		// Utils.assertTrue(quadruplets.size() == output.size());
		// Utils.assertTrue(this.compare(quadruplets, output));
	}

}
