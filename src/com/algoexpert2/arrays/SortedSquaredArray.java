package com.algoexpert2.arrays;

public class SortedSquaredArray {

	public int[] sortedSquaredArray(int[] array) {
		// Write your code here.
		int[] resultArr = new int[array.length];
		int smallIndex = 0;
		int largeIndex = array.length - 1;

		for (int i = array.length - 1; i >= 0; i--) {
			int smallValue = array[smallIndex];
			int largeValue = array[largeIndex];
			if (Math.abs(smallValue) > Math.abs(largeValue)) {
				resultArr[i] = smallValue * smallValue;
				smallIndex++;
			} else {
				resultArr[i] = largeValue * largeValue;
				largeIndex--;
			}
		}
		return resultArr;
	}

	public static void main(String[] args) {
		var input = new int[] { 1, 2, 3, 5, 6, 8, 9 };
		var expected = new int[] { 1, 4, 9, 25, 36, 64, 81 };
		var actual = new SortedSquaredArray().sortedSquaredArray(input);
	}

}
