package com.neetcode.math;

public class FindMissingObservations {

	public static int[] missingRolls(int[] rolls, int mean, int n) {

		int total = (rolls.length + n) * mean, curr = 0;
		for (int r : rolls) {
			curr += r;
		}
		int left = total - curr;
		if (left > n * 6 || left < n) {
			return new int[] {};
		}
		int[] res = new int[n];
		int average = left / n, offset = left % n;
		for (int i = 0; i < res.length; i++) {
			res[i] = average;
			res[i] += offset > 0 ? 1 : 0;
			offset--;
		}
		return res;
	}

	public static void main(String[] args) {
		// int[] rolls = {3,2,4,3};
		// int mean = 4, n = 2;

		int[] rolls = { 1, 5, 6 };
		int mean = 3, n = 4;

		missingRolls(rolls, mean, n);

	}
}
