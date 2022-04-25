package com.leetcodepatterns.dp;

public class HouseRobber {


	public void houseRobber(int[] nums) {
		int rob1 = 0;
		int rob2 = 0;

		for(int i= 0; i < nums.length; i++) {
			int newRob = Math.max(rob1 + nums[i], rob2);
			rob2 = rob1;
			rob1 = newRob;
		}

	}

}
