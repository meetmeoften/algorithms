package com.algoexpert2.greedy;

import java.util.Arrays;

public class TandemBicycle {

	public int tandemBicycle(int[] redShirtSpeeds, int[] blueShirtSpeeds, boolean fastest) {
		// Write your code here.

		Arrays.sort(redShirtSpeeds);
		Arrays.sort(blueShirtSpeeds);

		if (!fastest) {
			reverseArrayInPlace(redShirtSpeeds);
		}

		int totalSpeed = 0;
		for (int i = 0; i < redShirtSpeeds.length; i++) {
			int rider1 = redShirtSpeeds[i];
			int rider2 = blueShirtSpeeds[blueShirtSpeeds.length - 1 - i];
			totalSpeed += Math.max(rider1, rider2);
		}

		return totalSpeed;
	}

	public void reverseArrayInPlace(int[] array) {
		int start = 0;
		int end = array.length - 1;
		while (start < end) {
			int temp = array[start];
			array[start] = array[end];
			array[end] = temp;
			start++;
			end--;
		}
	}

	public static void main(String[] args) {
		int[] redShirtSpeeds = new int[] { 5, 5, 3, 9, 2 };
		int[] blueShirtSpeeds = new int[] { 3, 6, 7, 2, 1 };
		boolean fastest = true;
		int expected = 32;
		var actual = new TandemBicycle().tandemBicycle(redShirtSpeeds, blueShirtSpeeds, fastest);
		// Utils.assertTrue(expected == actual);
		System.out.println(actual);
	}

}
