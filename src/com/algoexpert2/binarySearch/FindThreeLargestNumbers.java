package com.algoexpert2.binarySearch;

public class FindThreeLargestNumbers {

	public static int[] findThreeLargestNumbers(int[] array) {
		// Write your code here.
		int[] largestArray = new int[] { Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE };

		for (int i : array) {
			if (i > largestArray[2]) {
				largestArray[0] = largestArray[1];
				largestArray[1] = largestArray[2];
				largestArray[2] = i;
			} else if (i > largestArray[1]) {
				largestArray[0] = largestArray[1];
				largestArray[1] = i;
			} else if (i > largestArray[0]) {
				largestArray[0] = i;
			}
		}
		return largestArray;
	}

	public static int[] findThreesmallest(int[] array) {
		int[] smallArray = new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE };

		for(int value : array) {
			if(value < smallArray[0]) {
				smallArray[2] = smallArray[1];
				smallArray[1] = smallArray[0];
				smallArray[0] = value;
			} else if (value < smallArray[1]) {
				smallArray[2] = smallArray[1];
				smallArray[1] = value;
			} else if(value < smallArray[2]) {
				smallArray[2] = value;
			}

		}

		return smallArray;
	}

	private void shift(int[] largestArray, int num, int shiftPosition) {
		for (int i = 0; i <= shiftPosition; i++) {
			if (shiftPosition == i) {
				largestArray[i] = num;
			} else {
				largestArray[i] = largestArray[i + 1];
			}

		}
	}

	public static void main(String[] args) {
		// System.out.println(findThreeLargestNumbers(new int[] { 141, 1, 17, -7, -17,
		// -27, 18, 541, 8, 7, 7 }));
		System.out.println(findThreeLargestNumbers(new int[] { 1, -1, 2, 3, 4, 5, 6, 0  }));
		System.out.println(findThreesmallest(new int[] { 1, -1, 2, 3, 4, 5, 6, 0  }));
	}
}
