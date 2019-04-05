package com.test.general;

import java.util.Arrays;

public class MaximumMoves {

	public static int minMoves(int[] nums) {
		int sum = 0, min = nums[0];
		for (int num : nums) {
			sum += num;
			min = Math.min(min, num);
		}
		return sum - nums.length * min;

	}

	public static int minMoves2(int[] nums) {
		Arrays.sort(nums);
		int i = 0, j = nums.length-1;
		int count = 0;
		while(i < j){
			count += nums[j]-nums[i];
			i++;
			j--;
		}
		return count;
	}


	public static void main(String[] args) {
		int[] arr = new int[] {1, 2, 3, 4};
		System.out.println(minMoves(arr));
		System.out.println(minMoves2(arr));
	}

}
