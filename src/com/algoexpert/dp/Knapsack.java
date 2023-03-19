package com.algoexpert.dp;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {

	public static List<List<Integer>> knapsackProblem(int[][] items, int capacity) {
		// Write your code here.
		// Replace the code below.

		int[][] values = new int[items.length+1][capacity +1];

		for(int i=1; i< items.length+1; i++) {
			int currentWeight = items[i-1][1];
			int currentValue = items[i-1][0];

			for(int c= 1; c < capacity +1 ; c++) {

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
				i--;
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
		//		int[][] input = {{1, 2}, {4, 3}, {5, 6}, {6, 7}};
		//		int[][] expected = {{10}, {1, 3}};

		int[][] input = {{1, 2}, {4, 3}};
		int[][] expected = {{10}, {1, 3}};

		// List<List<Integer>> result = knapsackProblem(input, 10);
		List<List<Integer>> result = knapsackProblem(input, 5);
		System.out.println(result);
	}
}
