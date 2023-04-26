package com.neetcode.slidingwindow;

import java.util.Arrays;

public class MaxFrequency {

	public static int maxFrequency(int[] nums, int k) {
		Arrays.sort(nums);

		int maxFrequency = 0;
		int leftPointer = 0, rightPointer = 0;
		long total = 0;
		while(rightPointer < nums.length){
			total += nums[rightPointer];
			while(nums[rightPointer]*(rightPointer - leftPointer + 1) - total >  k){
				total -= nums[leftPointer];
				leftPointer++;
			}
			//now we have a valid window
			maxFrequency = Math.max(maxFrequency, (rightPointer - leftPointer + 1));
			rightPointer++;
		}
		return maxFrequency;
	}

	public static void main(String[] args) {
		int[] nums = {1,2,4} ;
		maxFrequency(nums, 5);
	}

}
