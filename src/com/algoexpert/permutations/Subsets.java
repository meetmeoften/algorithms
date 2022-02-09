package com.algoexpert.permutations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Subsets {


	public List<List<Integer>> subsets(int[] nums) {
		if (nums == null || nums.length == 0) {
			return Collections.emptyList();
		}

		List<List<Integer>> result = new ArrayList<>();
		helper(nums, 0, new ArrayList<>(), result);
		return result;
	}

	private void helper(int[] nums, int idx, List<Integer> tempResult, List<List<Integer>> result) {
		System.out.println(tempResult);
		result.add(new ArrayList<>(tempResult));

		for (int i = idx; i < nums.length; i++) {
			tempResult.add(nums[i]);
			helper(nums, i + 1, tempResult, result);
			tempResult.remove(tempResult.size() - 1);
		}
	}

	public static void main(String[] args) {
		new Subsets().subsets(new int[] {1,2,3});
	}

}
