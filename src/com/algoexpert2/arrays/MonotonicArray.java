package com.algoexpert2.arrays;

public class MonotonicArray {

	public static boolean isMonotonic(int[] array) {
		// Write your code here.
		if (array.length <= 2) {
			return true;
		}

		var direction = array[1] - array[0];
		for (int i = 2; i < array.length; i++) {
			if (direction == 0) {
				direction = array[i] - array[i - 1];
				continue;
			}

			var difference = array[i] - array[i - 1];
			if (direction > 0 && difference < 0) {
				return false;
			} else if (direction < 0 && difference > 0) {
				return false;
			}

		}

		return true;
	}

	public static void main(String[] args) {
		var array = new int[] { -1, -5, -10, -1100, -1100, -1101, -1102, -9001 };
		var expected = true;
		var actual = isMonotonic(array);
		// Utils.assertEquals(expected, actual);
	}

}
