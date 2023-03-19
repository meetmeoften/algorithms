package com.algoexpert.arrays.leetcode300;

public class CanPlaceFlowers {

	public static boolean canPlaceFlowers(int[] flowerbed, int n) {
		int size = flowerbed.length;
		if (n == 0) {
			return true;
		}
		for (int i = 0; i < size; i++) {
			if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == size - 1 || flowerbed[i + 1] == 0)) {
				n--;
				if (n == 0) {
					return true;
				}
				flowerbed[i] = 1;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		canPlaceFlowers(new int[] {1,0,0,0,1}, 1);
	}
}
