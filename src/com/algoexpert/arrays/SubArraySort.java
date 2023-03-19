package com.algoexpert.arrays;

public class SubArraySort {

	public static int[] subarraySort(int[] array) {

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < array.length; i++) {
			int num = array[i];
			System.out.println(num);
			if (isOutOfOrder(i, num, array)) {
				min = Math.min(min, num);
				max = Math.max(max, num);
			}
		}

		if (min == Integer.MAX_VALUE) {
			return new int[] { -1, -1 };
		}

		int left = 0;
		while (min >= array[left]) {
			left++;
		}

		int right = array.length - 1;
		while (max <= array[right]) {
			right--;
		}
		return new int[] { left, right };
	}

	private static boolean isOutOfOrder(int i, int num, int[] array) {
		if (i == 0) {
			return num > array[i + 1];
		}
		if (i == array.length - 1) {
			return num < array[i - 1];
		}

		return num > array[i + 1] || num < array[i - 1];

	}


	public static void main(String[] args) {
		subarraySort(new int[] {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19});
	}

}
