package com.test.dynamicProgramming;

public class MinimumJumps {


	public static int minNumberOfJumps(int[] array) {
		// Write your code here.
		if(array.length == 1) {
			return 0;
		}
		int jumps = 0;
		int maxReach = array[0];
		int steps = array[0];

		for(int i=1; i < array.length-1; i++){
			maxReach = Math.max(maxReach, i+array[i]);
			steps--;

			if(steps == 0) {
				jumps++;
				steps = maxReach-i;
			}

		}
		return jumps+1;
	}


	public static boolean canJump(int[] nums) {

		int maxReach = 0;
		for(int i= 0; i< nums.length; i++) {
			if(maxReach < i) {
				return false;
			}

			maxReach = Math.max(maxReach, nums[i] + i);
		}
		return true;

	}

	public static int jump(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int currFarthest = 0;
		int jumps = 0;
		int currEnd = 0;

		for(int i= 0; i < nums.length; i++){
			if(currEnd < i) {
				currEnd = currFarthest;
				jumps++;
			}
			currFarthest = Math.max(currFarthest, i+nums[i]);
		}
		return jumps;

	}



	public static void main(String[] args) {
		int[] input = {3, 4, 2, 1, 2, 3};
		minNumberOfJumps(input);
		int[]  nums = {2,3,1,1,4};
		canJump(nums);
		jump(nums);
	}
}
