package com.algoexpert.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PerfectSquares {

	public static int numSquares(int n) {
		Queue<Integer> q = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();

		q.offer(0);
		visited.add(0);

		int level = 0;

		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				int currVal = q.poll();

				for (int j = 1; j * j <= n; j++) {
					int sum = j * j + currVal;

					if (sum == n) {
						return level + 1;
					}
					if (sum > n) {
						break;
					}

					if (!visited.contains(sum)) {
						q.offer(sum);
						visited.add(sum);
					}
				}
			}
			++level;
		}
		return n;
	}

	public static int numSquares2(int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		int ind = 1;

		while (ind * ind <= n) {
			int square = ind * ind;

			for (int i = square; i <= n; i++) {
				dp[i] = Math.min(dp[i - square] + 1, dp[i]);
			}
			ind++;
		}

		return dp[n];
	}

	public static int numSquares3(int n) {
		int[] dp = new int[n+1];
		dp[0] = 0;
		for(int i= 1; i*i <= n ; i++) {
			int square = i*i;
			for(int j= square; j <= n ; j++) {
				dp[j] = 1 + Math.min(dp[j-1], dp[Math.max(0, j-square)]);
			}
		}

		return dp[n];

	}

	public static void main(String[] args) {
		numSquares(12);
		//numSquares2(12);

		numSquares2(12);
	}

}
