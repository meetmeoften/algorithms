package com.algoexpert2.recursion;

import java.util.HashMap;
import java.util.Map;

public class NthFibonacci {

	public static int getNthFib(int n) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(1, 0);
		map.put(2, 1);
		return getNthFib(n, map);
	}

	public static int getNthFib(int n, Map<Integer, Integer> map) {
		if (map.containsKey(n)) {
			return map.get(n);
		}
		int val = getNthFib(n - 1, map) + getNthFib(n - 2, map);
		map.put(n, val);
		return val;
	}

	// ---------

	public static int getNthFib2(int n) {
		// Write your code here.
		int[] f = new int[n + 2];
		f[1] = 0;
		f[2] = 1;

		for (int i = 3; i <= n; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f[n];
	}

	public static void main(String[] args) {
		getNthFib(6);
		getNthFib2(6);
	}

}
