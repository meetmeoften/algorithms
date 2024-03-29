package com.algoexpert.binarysearch;

public class ValidPerfectSquare {

	public static boolean isPerfectSquare(int num) {
		int low = 1, high = num;

		while (low <= high) {
			long mid = (low + high) / 2;
			if (mid * mid == num) {
				return true;
			} else if (mid * mid < num) {
				low = (int) mid + 1;
			} else {
				high = (int) mid - 1;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		isPerfectSquare(16);
	}

}
