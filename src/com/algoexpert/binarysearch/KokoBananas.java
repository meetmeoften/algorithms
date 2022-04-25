package com.algoexpert.binarysearch;

public class KokoBananas {

	public static int minEatingSpeed(int[] piles, int H) {
		int lo = 1, hi = getMaxPile(piles);

		// Binary search to find the smallest valid K.
		while (lo <= hi) {
			int K = lo + ((hi - lo) >> 1);
			if (canEatAll(piles, K, H)) {
				hi = K - 1;
			} else {
				lo = K + 1;
			}
		}

		return lo;
	}

	private static boolean canEatAll(int[] piles, int K, int H) {
		int countHour = 0; // Hours take to eat all bananas at speed K.

		for (int pile : piles) {
			countHour += pile / K;
			if (pile % K != 0) {
				countHour++;
			}
		}
		return countHour <= H;
	}

	private static int getMaxPile(int[] piles) {
		int maxPile = Integer.MIN_VALUE;
		for (int pile : piles) {
			maxPile = Math.max(pile, maxPile);
		}
		return maxPile;
	}


	public static void main(String[] args) {
		minEatingSpeed(new int[] {3, 6, 7, 11}, 8);
	}

}
