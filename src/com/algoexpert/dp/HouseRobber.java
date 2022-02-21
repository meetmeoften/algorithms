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

}
