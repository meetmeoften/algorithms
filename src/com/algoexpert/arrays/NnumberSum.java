package com.algoexpert.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NnumberSum {

	public static List<List<Integer>> fourNumberSum(int[] array, int targetSum) {
		// Write your code here.
		Arrays.sort(array);
		return KSum_Recursion(array, 0, 4, targetSum);
	}

	static List<List<Integer>> KSum_Recursion(int[] nums, int index, int k, int target) {
		List<List<Integer>> kList = null;
		if(k == 2) {
			return twoSum(nums, index, nums.length - 1, target);
		}
		kList = new ArrayList<List<Integer>>();
		for (int i = index; i < nums.length - k + 1; i++) {
			var temp = KSum_Recursion(nums, i + 1, k - 1, target - nums[i]);
			if (temp != null && temp.size() > 0) {

				/************************************************************************/
				//Add the number which called KSum with k-1
				//For example if -2 called KSum_Recursion(k=2 & target=2)
				//KSum_Recursion(k=2 & target=2) would come back with [[1, 1], [0, 2]]
				//We would update those list such that they become [[-2, 1, 1], [-2, 0 ,2]]
				/************************************************************************/
				for (List<Integer> sumList : temp) {
					sumList.add(0, nums[i]);
				}
				kList.addAll(temp);
			}
			while (i < nums.length - k + 1 && nums[i] == nums[i + 1]) {
				i++;
			}
		}

		return kList;
	}

	static List<List<Integer>> twoSum(int[] nums, int left, int right, int target){
		var twosumList = new ArrayList<List<Integer>>();
		while(left < right) {
			var sum = nums[left] + nums[right];
			if(sum > target) {
				right--;
			} else if(sum < target){
				left++;
			} else {
				var temp = new ArrayList<Integer>();
				temp.add(nums[left]);
				temp.add(nums[right]);
				twosumList.add(temp);

				while(left < right && nums[left] == nums[left+1]) {
					left++;
				}

				while(left < right && nums[right] == nums[right-1]) {
					right--;
				}
				left++;
				right--;
			}
		}
		return twosumList;

	}

}
