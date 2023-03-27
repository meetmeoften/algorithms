package com.algoexpert2.arrays.hard;

import java.util.HashMap;
import java.util.Map;

public class LargestRange {

	public static int[] largestRange(int[] array) {
		// Write your code here.
		int[] bestRange = new int[2];
		int longestLength = 0;

		Map<Integer, Boolean> map = new HashMap<>();

		for (int num : array) {
			map.put(num, true);
		}

		for (int num : array) {
			if (!map.get(num)) {
				continue;
			}
			map.put(num, false);
			int currentLength = 1;
			int left = num - 1;
			int right = num + 1;

			while (map.containsKey(left)) {
				map.put(left, false);
				currentLength++;
				left--;
			}
			while (map.containsKey(right)) {
				map.put(right, false);
				currentLength++;
				right++;
			}
			if (currentLength > longestLength) {
				longestLength = currentLength;
				bestRange = new int[] { left + 1, right - 1 };
			}
		}
		return bestRange;
	}

	public static void main(String[] args) {
		int[] expected = { 0, 7 };
		largestRange(new int[] { 1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6 });
	}

}
