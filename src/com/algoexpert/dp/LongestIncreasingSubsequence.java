package com.algoexpert.dp;

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
		for(int i= 0; i < array.length; i++) {
			int current = array[i];
			for(int j= 0; j < i; j++) {
				int other = array[j];
				if(current > other && lengths[j] +1 >= lengths[i]) {
					lengths[i] = lengths[j] +1;
					sequences[i] = j;
				}
			}

			if(lengths[i] >= lengths[maxLengthIdx]) {
				maxLengthIdx = i;
			}
		}
		System.out.println(sequences);
		return buildSequence(array, sequences, maxLengthIdx);
	}

	private static List<Integer> buildSequence(int[] array, int[] sequences, int currentIndex) {
		List<Integer> sequence = new ArrayList<>();
		while(currentIndex != Integer.MIN_VALUE) {
			sequence.add(0, array[currentIndex]);
			currentIndex = sequences[currentIndex];
		}
		return sequence;
	}

}
