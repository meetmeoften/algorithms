package com.neetcode.arrays;

import java.util.HashMap;
import java.util.Map;

public class NumberOfPairsOfInterchangeableReactangles {

	public static long interchangeableRectangles(int[][] rectangles) {
		Map<Double, Long> map = new HashMap<>();
		long count = 0;
		for (int[] A : rectangles) {

			double x = A[0];
			double y = A[1];
			double division = x / y;

			if (map.containsKey(division)) {
				count += map.get(division);
			}
			map.put(division, map.getOrDefault(division, 0L) + 1);
		}
		return count;
	}

	public static void main(String[] args) {
		int[][] rectangles = {{4,8},{3,6},{10,20},{15,30}};
		interchangeableRectangles(rectangles);
	}

}
