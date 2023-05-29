package com.algoexpert2.arrays.hard;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MinRewards {

	public static int minRewards(int[] scores) {
		// Write your code here.
		int[] rewards = new int[scores.length];
		Arrays.fill(rewards, 1);

		for (int i = 1; i < scores.length; i++) {
			int j = i - 1;
			if (scores[i] > scores[j]) {
				rewards[i] = rewards[j] + 1;
			} else {
				while (j >= 0 && scores[j] > scores[j + 1]) {
					System.out.println(rewards[j] + "|" + rewards[j + 1]);
					rewards[j] = Math.max(rewards[j], rewards[j + 1] + 1);
					j--;
				}
			}
		}
		return IntStream.of(rewards).sum();
	}

	public static int minRewards3(int[] arr) {
		int[] result = new int[arr.length];
		Arrays.fill(result, 1);

		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > arr[i - 1]) {
				result[i] = result[i - 1] + 1;
			}
		}

		for (int i = arr.length - 1; i >= 1; i--) {
			if (arr[i - 1] > arr[i]) {
				if (result[i - 1] <= result[i]) {
					result[i - 1] = result[i] + 1;
				}
			}
		}

		int sum = 0;
		for (int res : result) {
			sum += res;
		}
		return sum;
	}

	public static int minRewards2(int[] scores) {
		int[] rewards = new int[scores.length];
		Arrays.fill(rewards, 1);
		for (int i = 1; i < scores.length; i++) {
			int j = i - 1;
			if (scores[i] > scores[j]) {
				rewards[i] = rewards[j] + 1;
			}
		}

		for (int i = scores.length - 2; i >= 0; i--) {
			int j = i + 1;
			if (scores[i] > scores[j]) {
				rewards[i] = Math.max(rewards[i], rewards[j] + 1);
			}
		}
		return IntStream.of(rewards).sum();
	}

	public static void main(String[] args) {
		minRewards(new int[] { 8, 4, 2, 1, 3, 6, 7, 9, 5 });
		minRewards2(new int[] { 8, 4, 2, 1, 3, 6, 7, 9, 5 });
		minRewards3(new int[] { 8, 4, 2, 1, 3, 6, 7, 9, 5 });
		// minRewards(new int[] {4, 6, 4, 5, 6, 2});
	}

}
