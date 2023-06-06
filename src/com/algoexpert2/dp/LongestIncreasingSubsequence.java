package com.algoexpert2.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence {

	public static List<Integer> longestIncreasingSubsequence(int[] array) {
		int[] sequences = new int[array.length];
		Arrays.fill(sequences, Integer.MIN_VALUE);
		int[] lengths = new int[array.length];
		Arrays.fill(lengths, 1);

		int maxLengthIdx = 0;
		for (int i = 0; i < array.length; i++) {
			int current = array[i];
			for (int j = 0; j < i; j++) {
				int other = array[j];
				if (current > other && lengths[j] + 1 >= lengths[i]) {
					lengths[i] = lengths[j] + 1;
					sequences[i] = j;
				}
			}

			if (lengths[i] >= lengths[maxLengthIdx]) {
				maxLengthIdx = i;
			}
		}
		System.out.println(sequences);
		return buildSequence(array, sequences, maxLengthIdx);
	}

	// ---------------
	public static List<Integer> longestIncreasingSubsequence2(int[] array) {
		int[] sequences = new int[array.length];
		int[] indices = new int[array.length + 1];
		Arrays.fill(indices, Integer.MIN_VALUE);
		int length = 0;
		for (int i = 0; i < array.length; i++) {
			int num = array[i];
			int newLength = binarySearch(1, length, indices, array, num);
			sequences[i] = indices[newLength - 1];
			indices[newLength] = i;
			length = Math.max(length, newLength);
		}
		return buildSequence(array, sequences, indices[length]);
	}

	public static int binarySearch(int start, int end, int[] indices, int[] array, int num) {
		if (start > end) {
			return start;
		}

		int mid = (start + end) / 2;
		if (array[indices[mid]] < num) {
			start = mid + 1;
		} else {
			end = mid - 1;
		}

		return binarySearch(start, end, indices, array, num);
	}

	private static List<Integer> buildSequence(int[] array, int[] sequences, int currentIndex) {
		List<Integer> sequence = new ArrayList<>();
		while (currentIndex != Integer.MIN_VALUE) {
			sequence.add(0, array[currentIndex]);
			currentIndex = sequences[currentIndex];
		}
		return sequence;
	}

	/**
	 *
	 *
	 * @param args
	 */

	public int findPositionToReplace(int[] a, int low, int high, int x) {
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (a[mid] == x) {
				return mid;
			} else if (a[mid] < x) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}

	public int lengthOfLIS2(int[] nums) {
		int n = nums.length;
		int len = 1;
		int[] dp = new int[n];
		dp[0] = nums[0];
		for (int i = 1; i < n; i++) {
			if (nums[i] > dp[len - 1]) {
				dp[len++] = nums[i];
			} else {
				int index = findPositionToReplace(dp, 0, len - 1, nums[i]);
				dp[index] = nums[i];
			}
		}

		return len;
	}

	public static void main(String[] args) {
		longestIncreasingSubsequence2(new int[] { 5, 7, -24, 12, 10, 2, 3, 12, 5, 6, 35 });
	}

}
