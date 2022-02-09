package com.algoexpert.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KSum {

	public static List<List<Integer>> fourNumberSum(int[] array, int targetSum) {
		Arrays.sort(array);
		return kSum(array, 0, 4, targetSum);
	}

	private static List<List<Integer>> kSum(int[] nums, int index, int k, int target) {
		if(k == 2) {
			return twoSum(nums, index, nums.length - 1, target);
		}

		List<List<Integer>> kList = new ArrayList<>();

		int length = nums.length - k + 1;
		for (int i = index; i < length; i++) {
			List<List<Integer>> temp = kSum(nums, i + 1, k - 1, target - nums[i]);
			if (temp != null && temp.size() > 0) {
				for (List<Integer> sumList : temp) {
					sumList.add(0, nums[i]);
				}
				kList.addAll(temp);
			}
			while (i < length && nums[i] == nums[i + 1]) {
				i++;
			}
		}
		return kList;
	}

	static List<List<Integer>> twoSum(int[] nums, int left, int right, int target){
		var twosumList = new ArrayList<List<Integer>>();

		while (left < right) {
			int sum = nums[left] + nums[right];
			if (sum == target) {
				var temp = new ArrayList<Integer>();
				temp.add(nums[left]);
				temp.add(nums[right]);
				twosumList.add(temp);
			}
			if (sum > target) {
				right--;
			} else {
				left++;
			}
		}
		return twosumList;
	}

	public static void main(String[] args) {
		fourNumberSum(new int[] {7, 6, 4, -1, 1, 2}, 16);
		List<Integer[]> quadruplets = new ArrayList<Integer[]>();
		quadruplets.add(new Integer[] {7, 6, 4, -1});
		quadruplets.add(new Integer[] {7, 6, 1, 2});
	}

}
