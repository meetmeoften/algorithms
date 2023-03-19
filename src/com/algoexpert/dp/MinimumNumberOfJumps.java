package com.algoexpert.dp;

import java.util.Arrays;

public class MinimumNumberOfJumps {

	public static int minNumberOfJumps(int[] array) {
		if(array.length == 1) {
			return 0;
		}
		int[] jumps = new int[array.length];
		Arrays.fill(jumps, Integer.MAX_VALUE);
		jumps[0] = 0;

		for(int i=1; i< array.length; i++) {
			for(int j= 0; j < i; j++) {
				if(array[j] + j >= i) {
					jumps[i] = Math.min(jumps[i] , jumps[j]+1);
				}
			}
		}
		return jumps[jumps.length-1];
	}

	public static int minNumberOfJumps2(int[] array) {
		if(array.length == 1) {
			return 0;
		}
		int jumps = 0;
		int maxReach = array[0];
		int steps = array[0];

		for(int i=1; i < array.length-1; i++){
			maxReach = Math.max(maxReach, i+ array[i]);
			System.out.println(maxReach);
			steps--;
			if(steps == 0) {
				jumps++;
				steps = maxReach-i;
			}

		}
		return jumps+1;
	}

	// Minimum Jump 1
	public boolean canJump(int[] nums) {

		if (nums == null || nums.length == 0) {
			return false;
		}

		int reachable = 0;
		for (int i = 0; i < nums.length; i++) {
			if (reachable < i) {
				return false;
			}

			reachable = Math.max(reachable, i + nums[i]);
		}

		return true;
	}

	// Minimum Jumps2
	public static int jump(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int currFarthest = 0;
		int jumps = 0;
		int currEnd = 0;

		for(int i= 0; i< nums.length; i++) {
			if(currEnd < i) {
				currEnd = currFarthest;
				jumps++;
			}
			currFarthest = Math.max(currFarthest, nums[i] +i);
		}
		return jumps;

	}

	public static void main(String[] args) {
		// int[] input = {3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3};

		int[] input = {2, 3, 1, 1, 4};
		minNumberOfJumps(input);
		jump(input);
	}

}
