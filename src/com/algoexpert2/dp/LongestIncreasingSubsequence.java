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

	public static void main(String[] args) {
		longestIncreasingSubsequence(new int[] { 5, 7, -24, 12, 10, 2, 3, 12, 5, 6, 35 });
	}

}
