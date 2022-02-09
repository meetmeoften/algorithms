package com.algoexpert.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TwoNumberSum {

	public static int[] twoNumberSum(int[] array, int targetSum) {
		// Write your code here.
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		for(int i=0; i < array.length; i++){
			var result = targetSum - array[i];
			var secondNum = map.get(result);
			if(secondNum == null){
				map.put(array[i], i);
			} else {
				return new int[] {array[secondNum], array[i]};
			}
		}
		return new int[0];
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


	public static void main(String[] args) {
		twoNumberSum(new int[] {3, 5, -4, 8, 11, 1, -1, 6}, 10);
	}

}
