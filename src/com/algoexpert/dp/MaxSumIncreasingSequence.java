package com.algoexpert.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxSumIncreasingSequence {

	public static List<List<Integer>> maxSumIncreasingSubsequence(int[] array) {
		// Write your code here.
		int[] sequences = new int[array.length];
		int[] sums = array.clone();
		Arrays.fill(sequences, Integer.MIN_VALUE);
		int maxSumIdx = 0;

		for(int i= 0; i< array.length; i++) {
			int currentNum = array[i];
			for(int j=0; j<i; j++) {
				int otherNum = array[j];
				int sumsValue = currentNum + sums[j];

				if(sumsValue >= sums[i] &&  currentNum > otherNum) {
					sums[i] = sumsValue;
					sequences[i] = j;
				}
			}

			if(sums[i] >= sums[maxSumIdx]) {
				maxSumIdx = i;
			}
		}

		return buildSequence(array, sequences, maxSumIdx, sums[maxSumIdx]);
	}

	public static List<List<Integer>> buildSequence(int[] array, int[] sequences,
			int currentIndex, int sums) {
		List<List<Integer>> sequence = new ArrayList<>();
		sequence.add(new ArrayList<Integer>());
		sequence.add(new ArrayList<Integer>());
		sequence.get(0).add(sums);

		while( currentIndex != Integer.MIN_VALUE) {
			sequence.get(1).add(0, array[currentIndex]);
			currentIndex = sequences[currentIndex];
		}
		return sequence;

	}

	public static void main(String[] args) {
		int[] input = {10, 70, 20, 30, 50, 11, 30};
		int[][] expected = {{110}, {10, 20, 30, 50}};
		maxSumIncreasingSubsequence(input);
	}

}
