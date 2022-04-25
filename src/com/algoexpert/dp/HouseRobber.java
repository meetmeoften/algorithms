package com.algoexpert.dp;

public class HouseRobber {

	public static int maxSubsetSumNoAdjacent(int[] array) {
		// Write your code here.
		if(array.length ==0) {
			return 0;
		}
		if(array.length == 1) {
			return array[0];
		}
		int[] maxSums = new int[array.length];
		maxSums[0] = array[0];
		maxSums[1] = Math.max(array[0], array[1]);
		for(int i= 2; i < array.length; i++) {
			maxSums[i] = Math.max(maxSums[i-1], maxSums[i-2] + array[i]);
		}

		return maxSums[array.length -1];
	}

	// Solution2
	public static int maxSubsetSumNoAdjacent2(int[] array) {
		// Write your code here.
		if(array.length ==0) {
			return 0;
		}
		if(array.length == 1) {
			return array[0];
		}
		int first = Math.max(array[0], array[1]);
		int second = array[0];
		for(int i= 2; i < array.length; i++) {
			int current = Math.max(first, second+ array[i]);
			second = first;
			first = current;
		}
		return first;
	}

	public int rob1(int[] array) {
		int rob1 = 0;
		int rob2 = 0;

		for(int i= 0; i < array.length; i++) {
			int temp = Math.max(rob1+ array[i], rob2);
			rob1 = rob2;
			rob2 = temp;

		}

		return rob2;
	}

	public int rob2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}

		return Math.max(rob(nums, 0, nums.length-2), rob(nums, 1, nums.length-1));
	}

	public int rob(int[] array, int start, int end) {
		int rob1 = 0;
		int rob2 = 0;

		for(int i= start; i <= end; i++) {
			int temp = Math.max(rob1+ array[i], rob2);
			rob1 = rob2;
			rob2 = temp;

		}
		return rob2;
	}

	public static void main(String[] args) {
		new HouseRobber().rob1(new int[] {1, 2, 3, 1});
	}

}
