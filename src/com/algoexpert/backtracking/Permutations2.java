package com.algoexpert.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Permutations2 {

	public List<List<Integer>> permuteUnique(int[] nums) {
		if (nums == null || nums.length == 0) {
			return Collections.emptyList();
		}

		Arrays.sort(nums);

		List<List<Integer>> result = new ArrayList<>();

		helper(nums, result, new ArrayList<>(), new boolean[nums.length]);
		return result;
	}

	private void helper(int[] nums, List<List<Integer>> result, List<Integer> temp, boolean[] used) {
		if (temp.size() == nums.length) {
			System.out.println(temp);
			result.add(new ArrayList<>(temp));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			System.out.println(i + " " + temp + "  " + used[i]);
			if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {  // should not change order
				continue;
			}

			used[i] = true;
			temp.add(nums[i]);

			helper(nums, result, temp, used);

			used[i] = false;
			temp.remove(temp.size() - 1);

			//			while(i+1 < nums.length && nums[i] == nums[i+1]) {
			//				i++;
			//			}
		}
	}

	public static void main(String[] args) {
		new Permutations2().permuteUnique(new int[] {1,2,1});
	}

}
