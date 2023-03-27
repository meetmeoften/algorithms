package com.algoexpert2.arrays;

import java.util.HashSet;

public class FirstDuplicateValue {

	public int firstDuplicateValue(int[] array) {
		// Write your code here.
		HashSet<Integer> hashSet = new HashSet<>();
		for (int value : array) {
			if (hashSet.contains(value)) {
				return value;
			}
			hashSet.add(value);
		}
		return -1;
	}

	public static void main(String[] args) {
		var input = new int[] { 2, 1, 5, 2, 3, 3, 4 };
		var expected = 2;
		var actual = new FirstDuplicateValue().firstDuplicateValue(input);
		// Utils.assertTrue(expected == actual);
	}

}
