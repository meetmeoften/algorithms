package com.algoexpert.arrays;

import java.math.BigDecimal;
import java.util.Optional;

public class FindClosestToZero {

	public static int findClosestNumber(int[] nums) {
		int min = Integer.MAX_VALUE;
		int ans = Integer.MIN_VALUE;

		for (int n : nums) {
			if (Math.abs(n) < min) {
				min = Math.abs(n);
				ans = n;
			} else if (Math.abs(n) == min) {
				ans = Math.max(ans, n);
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		int[] nums = { -4, -2, 1, 4, 8 };
		findClosestNumber(nums);

		Optional<BigDecimal> res = null;


	}

}

