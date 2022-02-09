package com.test.general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MinimumRewards {
	public static int minRewards(int[] scores) {
		// Write your code here.
		int[] rewards = new int[scores.length];
		Arrays.fill(rewards, 1);
		for(int i=1; i< scores.length; i++) {
			int j = i-1;
			if(scores[i] > scores[j]) {
				rewards[i] = rewards[j] +1;
			} else {
				while(j >=0 && scores[j] > scores[j+1]) {
					rewards[j] = Math.max(rewards[j], rewards[j+1]+1);
					j--;
				}
			}
		}
		return IntStream.of(rewards).sum();
	}

	public static int minRewards2(int[] scores) {
		// Write your code here.
		int[] rewards = new int[scores.length];
		Arrays.fill(rewards, 1);
		List<Integer> localMinIndexes = getLocalMinIndexes(scores);
		for(Integer localMinIndex: localMinIndexes) {
			expandFromLocalMinIndex(localMinIndex, scores, rewards);
		}
		return IntStream.of(rewards).sum();
	}

	public static List<Integer> getLocalMinIndexes(int[] array) {
		List<Integer> localMinIndexes = new ArrayList<>();
		if(array.length == 1 ) {
			localMinIndexes.add(0);
			return localMinIndexes;
		}
		for(int i= 0; i< array.length; i++) {

			if(i==0 && array[i] < array[i+1]) {
				localMinIndexes.add(i);
			}
			if(i == array.length -1 && array[i] < array[i-1]) {
				localMinIndexes.add(i);
			}

			if(i == 0 || i == array.length -1) {
				continue;
			}

			if(array[i] < array[i+1] && array[i] < array[i-1]) {
				localMinIndexes.add(i);
			}

		}
		return localMinIndexes;

	}

	public static void expandFromLocalMinIndex(int localMinIndex, int[] scores, int[] rewards) {
		int leftIndex = localMinIndex -1;
		while(leftIndex >= 0 && scores[leftIndex] > scores[leftIndex+1]) {
			rewards[leftIndex] = Math.max(rewards[leftIndex], rewards[leftIndex+1]+1);
			leftIndex--;
		}

		int rightIndex = localMinIndex +1;
		while(rightIndex < scores.length && scores[rightIndex] > scores[rightIndex-1]) {
			rewards[rightIndex] = rewards[rightIndex-1]+1;
			rightIndex++;
		}

	}

	public static void main(String[] args) {
		minRewards2(new int[] {8, 4, 2, 1, 3, 6, 7, 9, 5});
	}
}
