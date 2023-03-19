package com.algoexpert.arrays;

public class LongestPeak {

	public static int longestPeak(int[] array) {
		// Write your code here.
		int longestPeakLength = 0;
		int i = 1;
		while (i < array.length - 1) {
			boolean isPeak = array[i] > array[i - 1] && array[i] > array[i + 1];

			if (!isPeak) {
				i++;
				continue;
			}

			System.out.println(i);
			int leftIdx = i - 2;
			while (leftIdx >= 0 && array[leftIdx] < array[leftIdx + 1]) {
				leftIdx -= 1;
			}

			int rightIdx = i + 2;
			while (rightIdx < array.length && array[rightIdx] < array[rightIdx - 1]) {
				rightIdx += 1;
			}
			System.out.println("leftIdx  " + leftIdx + "  rightIdx " + rightIdx);
			int currentPeakLength = rightIdx - leftIdx - 1;
			longestPeakLength = Math.max(longestPeakLength, currentPeakLength);
			// i=rightIdx;
			i++;
		}
		return longestPeakLength;
	}

	public static void main(String[] args) {
		var input = new int[] {1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3};
		longestPeak(input);
	}
}
