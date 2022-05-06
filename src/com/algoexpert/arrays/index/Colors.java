package com.algoexpert.arrays.index;

public class Colors {

	public static int minCost(String s, int[] cost) {
		int n = s.length();
		int result = 0;
		for (int i=1; i<n; i++) {
			if (s.charAt(i) == s.charAt(i-1)) {
				result = result + Math.min(cost[i], cost[i-1]);
				cost[i] = Math.max(cost[i], cost[i-1]);
			}
		}
		return result;

	}

	public static void main(String[] args) {
		//	Input: colors = "aabaa", neededTime = [1,2,3,4,1]
		//		Output: 2

		int[] arr = new int[] {1, 2, 3, 4, 10};
		System.out.println(minCost("aabaa", arr));
	}

}
