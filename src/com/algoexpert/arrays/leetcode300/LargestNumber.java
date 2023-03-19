package com.algoexpert.arrays.leetcode300;

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {

	public static String largestNumber(int[] nums) {
		String[] arr = new String[nums.length];

		for (int i = 0; i < nums.length; i++) {
			arr[i] = String.valueOf(nums[i]);
		}

		Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				String n1 = s1 + s2;
				String n2 = s2 + s1;
				return n2.compareTo(n1);
			}
		});

		if (arr[0].equals("0")) {
			return "0";
		}

		StringBuilder sb = new StringBuilder();
		for (String s : arr) {
			sb.append(s);
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		int[] nums = { 10, 2, 0 };
		largestNumber(nums);
	}

}
