package com.algoexpert.arrays;

import java.util.Arrays;

public class SmallestDifference {

	public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
		// Write your code here.
		Arrays.sort(arrayOne);
		Arrays.sort(arrayTwo);
		int i = 0;
		int j = 0;

		int smallest = Integer.MAX_VALUE;
		int current = Integer.MAX_VALUE;

		int[] smallestPair = new int[2];
		while (i < arrayOne.length && j < arrayTwo.length) {
			int firstNum = arrayOne[i];
			int secondNum = arrayTwo[j];

			if (firstNum > secondNum) {
				current = firstNum - secondNum;
				j++;
			} else if (secondNum > firstNum) {
				current = secondNum - firstNum;
				i++;
			} else {
				return new int[] { firstNum, secondNum };
			}

			if (current < smallest) {
				smallest = current;
				smallestPair = new int[] { firstNum, secondNum };
			}

		}

		return smallestPair;
	}

	public static void main(String[] args) {
		smallestDifference(new int[] { -1, 5, 10, 20, 28, 3 }, new int[] { 26, 134, 135, 15, 17 });
	}

}
