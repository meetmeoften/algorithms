package com.test.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class KnapsackAlgoExpert {

	public static List<List<Integer>> knapsackProblem(int[][] items, int capacity) {
		// Write your code here.
		// Replace the code below.

		int[][] values = new int[items.length+1][capacity +1];

		for(int i=1; i< items.length+1; i++) {
			int currentWeight = items[i-1][1];
			int currentValue = items[i-1][0];

			for(int c= 0; c < capacity +1 ; c++) {

				if(currentWeight > c) {
					values[i][c] = values[i-1][c];
				} else {
					values[i][c] = Math.max(values[i-1][c], currentValue + values[i-1] [c - currentWeight]);
				}
			}
		}
		return getKnapsackItems(values, items, values[items.length][capacity]);
	}

	public static List<List<Integer>> getKnapsackItems(int[][] values, int[][] items, int weight) {
		List<List<Integer>> sequence = new ArrayList<>();
		List<Integer> totalWeight = new ArrayList<>();
		totalWeight.add(weight);
		sequence.add(totalWeight);
		sequence.add(new ArrayList<Integer>());
		int i= values.length -1;
		int c = values[0].length -1;
		while (i > 0) {
			if(values[i][c] == values[i-1][c]) {
				i= i-1;
			} else {
				sequence.get(1).add(0, i-1);
				c = c - items[i-1][1];
				i--;
			}
			if(c==0) {
				break;
			}
		}
		return sequence;
	}

	public static void main(String[] args) {
		int[][] input = {{1, 2}, {4, 3}, {5, 6}, {6, 7}};
		int[][] expected = {{10}, {1, 3}};
		knapsackProblem(input, 10);
	}


	private static boolean compare(List<List<Integer>> arr1, int[][] arr2) {
		if (arr1.get(0).get(0) != arr2[0][0]) {
			return false;
		}
		if (arr1.get(1).size() != arr2[1].length) {
			return false;
		}
		for (int i = 0; i < arr1.get(1).size(); i++) {
			if (arr1.get(1).get(i) != arr2[1][i]) {
				return false;
			}
		}
		return true;
	}

}
