package com.algoexpert.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class IPO {

	public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
		int length = profits.length;
		int[][] profitsCapital = new int[length][2];
		for (int i = 0; i < length; i++) {
			profitsCapital[i][0] = profits[i];
			profitsCapital[i][1] = capital[i];
		}
		Arrays.sort(profitsCapital, (a, b) -> a[1] - b[1]);

		int totalCapital = w;
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>((a, b) -> b - a);
		int index = 0;
		while (k > 0) {
			while (index < length && profitsCapital[index][1] <= totalCapital) {
				priorityQueue.offer(profitsCapital[index][0]);
				index++;
			}
			if (priorityQueue.isEmpty()) {
				break;
			}
			totalCapital += priorityQueue.poll();
			k--;
		}
		return totalCapital;
	}

	public static void main(String[] args) {
		int k = 2, w = 0;
		int[] profits = { 1, 2, 3 }, capital = { 0, 1, 1 };

		findMaximizedCapital(k, w, profits, capital);
	}

}
