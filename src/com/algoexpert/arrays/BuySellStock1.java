package com.algoexpert.arrays;

public class BuySellStock1 {

	public static void buySellStock(int[] array) {
		int left = 0;
		int right = 1;
		int maxP = 0;

		while(right < array.length) {
			if(array[left] < array[right]) {
				int profit = array[right] - array[left];
				maxP = Math.max(maxP, profit);
			} else {
				left = right;
			}
			right++;
		}
	}
}
