package com.algoexpert.stack;

import java.util.Stack;

public class Find132Pattern {

	public static boolean find132pattern(int[] nums) {
		if (nums.length < 3) {
			return false;
		}
		Stack<Integer> stack = new Stack<>();
		int[] min = new int[nums.length];
		min[0] = nums[0];

		for (int i = 1; i < nums.length; i++) {
			min[i] = Math.min(min[i - 1], nums[i]);
		}

		for (int j = nums.length - 1; j >= 0; j--) {
			if (nums[j] > min[j]) {
				while (!stack.isEmpty() && stack.peek() <= min[j]) {
					stack.pop();
				}
				if (!stack.isEmpty() && stack.peek() < nums[j]) {
					return true;
				}
				stack.push(nums[j]);
			}
		}
		return false;
	}

	public static boolean find132pattern2(int[] nums) {
		Stack<Integer> st = new Stack<>();
		int secondMax = Integer.MIN_VALUE;

		for (int i = nums.length - 1; i >= 0; i--) {
			if (nums[i] < secondMax) {
				return true;
			}
			while (!st.isEmpty() && nums[i] > st.peek()) {
				secondMax = Math.max(secondMax, st.pop());
			}
			st.push(nums[i]);
		}

		return false;
	}

	public static void main(String[] args) {
		int[] nums = { 3, 1, 4, 2 };
		find132pattern(nums);
		find132pattern2(nums);
	}

}
