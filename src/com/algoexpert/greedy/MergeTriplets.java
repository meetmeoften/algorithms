package com.algoexpert.greedy;

public class MergeTriplets {

	public static boolean mergeTriplets(int[][] t, int[] target) {
		boolean[] ans = new boolean[3];

		for (int i = 0; i < t.length; i++) {
			if (t[i][0] > target[0] || t[i][1] > target[1] || t[i][2] > target[2]) {
				continue;
			}
			for (int j = 0; j < 3; j++) {
				if (t[i][j] == target[j]) {
					ans[j] = true;
				}
			}
		}
		return ans[0] && ans[1] && ans[2];
	}

	public static void main(String[] args) {
		// int[][] triplets = {{2,5,3},{1,8,4},{1,7,5}};
		// int[] target = { 2, 7, 5 };
		int[][] triplets = { { 3, 5, 1 }, { 10, 5, 7 } };
		int[] target = { 3, 5, 7 };

		mergeTriplets(triplets, target);
	}

}
