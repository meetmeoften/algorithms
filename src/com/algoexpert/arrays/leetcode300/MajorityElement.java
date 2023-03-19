package com.algoexpert.arrays.leetcode300;

import java.util.HashMap;

public class MajorityElement {

	public static int majorityElement(int[] nums) {
		int res = 0, count = 0;

		for (int n : nums) {
			if (count == 0) {
				res = n;
			}
			if(n == res) {
				count++;
			} else {
				count--;
			}

			//count += (n == res ? 1 : -1);
		}

		return res;
	}

	public static int majorityElement2(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i]) + 1);
			} else {
				map.put(nums[i], 1);
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i]) && map.get(nums[i]).intValue() > nums.length / 2) {
				return nums[i];
			}
		}
		return -1;
	}


	public static void main(String[] args) {
		majorityElement(new int[] {3,2,3});
	}

}
