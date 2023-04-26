package com.neetcode.math;

public class ReverseInteger {

	public static int reverse(int x) {
		boolean isNegative = x < 0;
		x = Math.abs(x);
		int num = 0;

		while (x > 0) {
			if (Integer.MAX_VALUE / 10 < num) {
				return 0;
			}

			num = 10 * num + x % 10;
			x /= 10;
		}

		return isNegative ? -num : num;
	}

	public static int reverse2(int x) {
		long res = 0;
		while (x != 0) {
			res = res * 10 + (x % 10);
			x /= 10;
		}
		return res < Integer.MIN_VALUE || res > Integer.MAX_VALUE ? 0 : (int) res;
	}

	public static void main(String[] args) {
		System.out.println(reverse2(-123));
	}

}
