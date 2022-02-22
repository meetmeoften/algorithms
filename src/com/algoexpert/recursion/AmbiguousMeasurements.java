package com.algoexpert.recursion;

import java.util.HashMap;

public class AmbiguousMeasurements {

	public static boolean ambiguousMeasurements(int[][] measuringCups, int low, int high) {
		// Write your code here.
		HashMap<String, Boolean> memoization = new HashMap<>();
		return canMeasureInrange(measuringCups, low, high, memoization);
	}

	public static boolean canMeasureInrange(int[][] measuringCups, int low, int high, HashMap<String, Boolean> memoization) {
		String memoizeKey = createHashableKey(low, high);
		if (memoization.containsKey(memoizeKey)) {
			return memoization.get(memoizeKey);
		}

		if (low <= 0 && high <= 0) {
			return false;
		}
		boolean canMeasure = false;
		for (int[] cup : measuringCups) {
			int cupLow = cup[0];
			int cupHigh = cup[1];
			if (low <= cupLow && cupHigh <= high) {
				canMeasure = true;
				break;
			}

			int newLow = Math.max(0, low - cupLow);
			int newHigh = Math.max(0, high - cupHigh);
			canMeasure = canMeasureInrange(measuringCups, newLow, newHigh, memoization);
			if (canMeasure) {
				break;
			}
		}
		memoization.put(memoizeKey, canMeasure);
		return canMeasure;
	}

	public static String createHashableKey(int low, int high) {
		return String.valueOf(low) + ":" + String.valueOf(high);
	}

	public static void main(String[] args) {
		int[][] cups = new int[][] {{200, 210}, {450, 465}, {800, 850}};
		int low = 2100;
		int high = 2300;
		boolean expected = true;
		var actual = ambiguousMeasurements(cups, low, high);
	}

}
