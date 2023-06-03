package com.algoexpert2.arrays;

public class MajorityElement {

	public static int majorityElement(int[] array) {
		// Write your code here.
		int count = 0;
		int answer = array[0];

		for (int value : array) {
			if (count == 0) {
				answer = value;
			}

			if (value == answer) {
				count++;
			} else {
				count--;
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		var input = new int[] { 1, 2, 3, 2, 2, 1, 2 };
		var expected = 2;
		majorityElement(input);
	}

}
