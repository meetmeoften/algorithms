package com.algoexpert.dp;

import java.util.ArrayList;
import java.util.List;

public class DiskStacking {

	public static List<Integer[]> diskStacking(List<Integer[]> disks) {
		disks.sort((d1, d2) -> d1[2].compareTo(d2[2]));

		int[] heights = new int[disks.size()];
		for(int i=0; i < disks.size(); i++) {
			heights[i] = disks.get(i)[2];
		}

		int[] sequences = new int[disks.size()];
		for(int i=0; i < disks.size(); i++) {
			sequences[i] = Integer.MIN_VALUE;
		}

		int maxHeightIndex = 0;
		for(int i= 1; i < disks.size(); i++) {
			Integer[] current = disks.get(i);
			for(int j= 0; j<i; j++) {
				Integer[] other = disks.get(j);

				if(valid(other, current)) {
					int currHeight = current[2] + heights[j];
					if(currHeight >= heights[i]) {
						heights[i] = currHeight;
						sequences[i] = j;
					}
				}
			}
			if(heights[i] >= heights[maxHeightIndex]) {
				maxHeightIndex = i;
			}
		}
		return buildSequence(disks, sequences, maxHeightIndex);
	}

	private static List<Integer[]> buildSequence(List<Integer[]> array, int[] sequences, int currentIndex) {
		List<Integer[]> sequence = new ArrayList<>();

		while(currentIndex != Integer.MIN_VALUE) {
			sequence.add(0, array.get(currentIndex));
			currentIndex = sequences[currentIndex];
		}
		return sequence;
	}

	public static boolean valid(Integer[] other, Integer[] current) {
		return current[0] > other[0] &&
				current[1] > other[1] &&
				current[2] > other[2];
	}

	public static void main(String[] args) {
		List<Integer[]> input = new ArrayList<Integer[]>();
		input.add(new Integer[] {2, 1, 2});
		input.add(new Integer[] {3, 2, 3});
		input.add(new Integer[] {2, 2, 8});
		input.add(new Integer[] {2, 3, 4});
		input.add(new Integer[] {2, 2, 1});
		input.add(new Integer[] {4, 4, 5});
		List<Integer[]> expected = new ArrayList<Integer[]>();
		expected.add(new Integer[] {2, 1, 2});
		expected.add(new Integer[] {3, 2, 3});
		expected.add(new Integer[] {4, 4, 5});
		diskStacking(input);
	}

}
