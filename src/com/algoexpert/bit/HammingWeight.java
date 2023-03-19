package com.algoexpert.bit;

public class HammingWeight {

	public static int hammingWeight(int n) {

		int count = 0;

		while (n != 0) {

			// Check if the number is odd
			if ((n & 1) == 1) {
				count++;
			}

			n = n >>> 1;
		}

		return count;
	}

	public static void main(String[] args) {
		hammingWeight(00000000000000000000000000001011);
	}

}
