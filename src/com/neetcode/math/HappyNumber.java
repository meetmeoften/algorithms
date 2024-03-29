package com.neetcode.math;

import java.util.HashSet;

public class HappyNumber {

	public static boolean isHappy(int n) {
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(n);
		while (n != 1) {
			int result = 0;
			while (n != 0) {
				System.out.println(n);
				result += Math.pow(n % 10, 2);
				n /= 10;
			}
			if (set.contains(result)) {
				return false;
			}
			set.add(result);
			n = result;
		}
		return true;
	}

	public static void main(String[] args) {
		isHappy(2);
	}

}
