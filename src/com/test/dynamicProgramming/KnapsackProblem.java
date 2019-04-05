package com.test.dynamicProgramming;

import java.util.Scanner;

public class KnapsackProblem {

	/**
	 * 1
	 * 2
	 * 37 7
	 * 28 76
	 * 65
	 *
	 *
	 * Result : 37
	 * @param args
	 */

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int a0 = 0; a0 < t; a0++) {
			int n = in.nextInt();
			int[] val = new int[n];
			int[] wt = new int[n];
			for (int i = 0; i < n; i++) {
				val[i] = in.nextInt();
				wt[i] = in.nextInt();
			}
			int weight = in.nextInt();
			int result = bottomUpDP(val, wt, weight);
			System.out.println(result);
			in.close();
		}
	}

	public static int bottomUpDP(int val[], int wt[], int W) {
		int K[][] = new int[val.length + 1][W + 1];
		for (int i = 0; i <= val.length; i++) {
			for (int j = 0; j <= W; j++) {
				if (i == 0 || j == 0) {
					K[i][j] = 0;
					continue;
				}
				if (j - wt[i - 1] >= 0) {
					K[i][j] = Math.max(K[i - 1][j],
							K[i - 1][j - wt[i - 1]] + val[i - 1]);
				} else {
					K[i][j] = K[i - 1][j];
				}
			}
		}
		return K[val.length][W];
	}

}
