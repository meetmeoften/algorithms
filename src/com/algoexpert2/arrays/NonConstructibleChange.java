package com.algoexpert2.arrays;

import java.util.Arrays;

public class NonConstructibleChange {

	public int nonConstructibleChange(int[] coins) {
		// Write your code here.
		Arrays.sort(coins);

		int currentChange = 0;
		for (int coin : coins) {
			System.out.println(coin);
			if (coin > currentChange + 1) {
				return currentChange + 1;
			}
			currentChange = currentChange + coin;

		}

		return currentChange + 1;
	}

	public static void main(String[] args) {
		int[] input = new int[] { 5, 7, 1, 1, 2, 3, 22 };
		int expected = 20;
		var actual = new NonConstructibleChange().nonConstructibleChange(input);
	}

}
