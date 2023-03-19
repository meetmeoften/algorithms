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


	public static int minEatingSpeed2(int[] piles, int h) {
		int left = 1, right = 1;
		for (int pile : piles) {
			right = Math.max(right, pile);
		}

		while (left <= right) {
			// Get the middle index between left and right boundary indexes.
			// hourSpent stands for the total hour Koko spends.
			int middle = (left + right) / 2;
			int hourSpent = 0;

			// Iterate over the piles and calculate hourSpent.
			// We increase the hourSpent by ceil(pile / middle)
			for (int pile : piles) {
				hourSpent += Math.ceil((double) pile / middle);
			}

			// Check if middle is a workable speed, and cut the search space by half.
			if (hourSpent <= h) {
				right = middle-1;
			} else {
				left = middle + 1;
			}
		}

		// Once the left and right boundaries coincide, we find the target value,
		// that is, the minimum workable eating speed.
		return left;
	}


	public static void main(String[] args) {
		//minEatingSpeed(new int[] {3, 6, 7, 11}, 8);
		minEatingSpeed2(new int[] {3, 6, 7, 11}, 8);
	}

}
