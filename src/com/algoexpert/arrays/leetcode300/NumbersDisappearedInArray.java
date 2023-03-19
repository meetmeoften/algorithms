package com.algoexpert.arrays.leetcode300;

import java.util.ArrayList;
import java.util.List;

public class NumbersDisappearedInArray {

	public static List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < nums.length; i++) {
			System.out.println(i);
			int correct = nums[i] - 1;
			if (nums[i] != nums[correct]) {
				int temp = nums[i];
				nums[i] = nums[correct];
				nums[correct] = temp;
				i--;
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] - 1 != i) {
				list.add(i + 1);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		int[] nums1 = { 4,3,2,7,8,2,3,1};
		//		int[] nums1 = { 2, 2};
		findDisappearedNumbers(nums1);
	}

}
