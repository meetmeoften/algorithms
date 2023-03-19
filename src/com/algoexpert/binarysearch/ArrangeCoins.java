package com.algoexpert.binarysearch;

public class ArrangeCoins {

	public static int arrangeCoins(int n) {
		long left = 0; // we use "long" because we may get an integer overflow
		long right = n;

		while (left <= right) {
			long pivot = left + (right - left) / 2;
			long coinsUsed = pivot * (pivot + 1) / 2;
			if (coinsUsed == n) {
				return (int) pivot;
			}
			if (n < coinsUsed) {
				right = pivot - 1;
			} else {
				left = pivot + 1;
			}
		}
		return (int) right;
	}

	public static int arrangeCoins2(int n) {
		int count = 0;
		int level = 1;
		while(n >= level){
			count++;
			n = n - level;
			level++;
		}
		return count;
	}

	public static void main(String[] args) {
		arrangeCoins(5);
		arrangeCoins2(5);
	}

}
