package com.algoexpert.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets2 {

	public List<List<Integer>> subsetsWithDup(int[] nums) {
		if (nums == null || nums.length == 0) {
			return new ArrayList<>();
		}

		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<>();
		helper(nums, 0, result, new ArrayList<>());
		return result;
	}

	private void helper(int[] nums, int idx, List<List<Integer>> result, List<Integer> temp) {
		System.out.println(idx + " -- " + temp);
		result.add(new ArrayList<>(temp));

		for (int i = idx; i < nums.length; i++) {
			if (i > idx && nums[i] == nums[i - 1]) {
				continue;
			}
			temp.add(nums[i]);
			helper(nums, i + 1, result, temp);
			temp.remove(temp.size() - 1);
		}
	}

	public static void main(String[] args) {
		new Subsets2().subsetsWithDup(new int[] {1, 2, 2});
	}

}
