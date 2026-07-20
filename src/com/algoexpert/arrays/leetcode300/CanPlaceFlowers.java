package com.algoexpert.arrays.leetcode300;

public class CanPlaceFlowers {

	public static boolean canPlaceFlowers(int[] flowerbed, int n) {
		int size = flowerbed.length;
		if (n == 0) {
			return true;
		}
		for (int i = 0; i < size; i++) {
//			if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == size - 1 || flowerbed[i + 1] == 0)) {
//				n--;
//				if (n == 0) {
//					return true;
//				}
//				flowerbed[i] = 1;
//			}
			if (flowerbed[i] == 1)
				continue;

			boolean leftEmpty = (i == 0) || flowerbed[i - 1] == 0;
			boolean rightEmpty = (i == flowerbed.length - 1)
					|| flowerbed[i + 1] == 0;

			if (leftEmpty && rightEmpty) {
				flowerbed[i] = 1;
				n--;
			}
			if (n == 0) {
				return true;
			}
		}
		return false;
	}

	public boolean canPlaceFlowersCircular(int[] flowerbed, int n) {
		int len = flowerbed.length;

		if (n == 0) return true;

		// Single plot
		if (len == 1) {
			return flowerbed[0] == 0 && n <= 1;
		}

		for (int i = 0; i < len; i++) {
			int left = (i - 1 + len) % len;
			int right = (i + 1) % len;

			if (flowerbed[i] == 0 &&
					flowerbed[left] == 0 &&
					flowerbed[right] == 0) {

				flowerbed[i] = 1;
				n--;

				if (n == 0) {
					return true;
				}
			}
		}

		return false;
	}

	public static void main(String[] args) {
		canPlaceFlowers(new int[] {1,0,0,0,1}, 1);
	}
}
