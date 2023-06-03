package com.algoexpert2.arrays;

public class LongestSubArrayWithSum {



	public static int[] longestSubarrayWithSum(int[] array, int targetSum) {
		int[] result = new int[] {};
		int currSum = 0;

		int start= 0;
		int end= 0;

		while(end < array.length) {
			currSum += array[end];

			while(start < end && currSum > targetSum) {
				currSum -= array[start];
				start++;
			}

			if(currSum == targetSum) {
				if(result.length == 0 || result[1] - result[0] < end - start) {
					result = new int[] {start, end};
				}
			}
			end++;

		}

		return result;
	}


	public static int[] longestSubarrayWithSum2(int[] array, int targetSum) {
		int[] result = new int[] {};
		for(int i= 0; i < array.length; i++) {
			int currSum = 0;
			for(int j=i; j < array.length; j++) {
				currSum += array[j];
				if(currSum == targetSum) {
					if(result.length == 0 || result[1] - result[0] < j - i) {
						result = new int[] {i, j};
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] array = new int[] {1, 2, 3, 4, 3, 3, 1, 2, 1};
		int targetSum = 10;
		int[] expected = new int[] {4, 8};
		var actual = longestSubarrayWithSum(array, targetSum);
	}

}
