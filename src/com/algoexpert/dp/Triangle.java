package com.algoexpert.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Triangle {

	// https://leetcode.com/problems/triangle/solutions/2788755/java-2-solutions-dp-top-down-bottom-up/  -- MUST WATCH

	public static int minimumTotal(List<List<Integer>> triangle) {
		int l = triangle.size();
		int[] dp = new int[l + 1];

		for (int row = l - 1; row >= 0; row--) {
			for (int col = 0; col < row + 1; ++col) {
				int triangleValue = triangle.get(row).get(col);
				int minValue = Math.min(dp[col], dp[col + 1]);
				dp[col] = triangleValue + minValue;
			}
		}

		return dp[0];
	}

	public static void main(String[] args) {
		Integer[][] triangle = { { 2 }, { 3, 4 }, { 6, 5, 7 }, { 4, 1, 8, 3 } };

		List<List<Integer>> resList = new ArrayList<>();
		for(Integer[] rows : triangle) {
			resList.add(Arrays.stream(rows).collect(Collectors.toList()));
		}
		minimumTotal(resList);
	}

}
