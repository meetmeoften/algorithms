package com.algoexpert.permutations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permutations {

	public List<List<Integer>> permute(int[] nums) {
		if (nums == null || nums.length == 0) {
			return Collections.emptyList();
		}

		List<List<Integer>> result = new ArrayList<>();
		helper(nums, result, new ArrayList<>());

		return result;
	}

	private void helper(int[] nums, List<List<Integer>> result, List<Integer> temp) {
		System.out.println(temp);
		if (temp.size() == nums.length) {
			//			System.out.println(temp);
			result.add(new ArrayList<>(temp));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			//			System.out.println(temp);
			if (temp.contains(nums[i])) {
				continue;
			}

			temp.add(nums[i]);
			helper(nums, result, temp);
			temp.remove(temp.size() - 1);
		}
	}

	public static void main(String[] args) {
		new Permutations().permute(new int[] {1,2,3});
	}

}
