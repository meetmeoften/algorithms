package com.leetcodepatterns.dp;

public class RopeColorful {


	public int minCost(String s, int[] cost) {
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
		//		new RopeColorful().minCost("abaac", new int[] {1,2,3,4,5});
		new RopeColorful().minCost("aaabbbabbbb", new int[] {3,5,10,7,5,3,5,5,4,8,1});
	}
}
