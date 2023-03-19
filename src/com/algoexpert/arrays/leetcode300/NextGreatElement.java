package com.algoexpert.arrays.leetcode300;

import java.util.HashMap;
import java.util.Stack;

public class NextGreatElement {

	public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
		int[] ans = new int[nums1.length];

		Stack<Integer> stack = new Stack<>();
		HashMap<Integer, Integer> map = new HashMap<>();

		for(int num: nums2) {
			while(!stack.isEmpty() && num > stack.peek()) {
				map.put(stack.pop(), num);
			}
			stack.add(num);
		}

		int i = 0;
		for(int num : nums1) {
			ans[i++] = map.getOrDefault(num, -1);
		}
		return ans;
	}

	public static void main(String[] args) {
		int[] nums1 = {4,1,2};
		int[] nums2 = {1,3,4,2};
		nextGreaterElement(nums1, nums2);
	}

}
