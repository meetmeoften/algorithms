package com.test.general;

import java.util.Scanner;

public class HackerRankScanner {

	static String gridChallenge(String[] grid) {
		return null;
	}

	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		// int t = in.nextInt();
		// int n = in.nextInt();
		// String[] grid = new String[n];
		// for(int grid_i = 0; grid_i < n; grid_i++){
		// grid[grid_i] = in.next();
		// }
		// String result = gridChallenge(grid);
		// System.out.println(result);
		// in.close();

		//		/**
		//		 * 1
		//		 * 5
		//		 * 10 1 10 1 10
		//		 */
		//		Scanner in = new Scanner(System.in);
		//		System.out.println("Enter total testcases");
		//		int t = in.nextInt();
		//		for (int a0 = 0; a0 < t; a0++) {
		//
		//			int n = in.nextInt();
		//			int[][] arr = new int[n][2];
		//			System.out.println("Enter sub testcases value");
		//			for (int arr_i = 0; arr_i < n; arr_i++) {
		//				for (int arr_j = 0; arr_j < 2; arr_j++) {
		//					arr[arr_i][arr_j] = in.nextInt();
		//				}
		//			}
		//			System.out.println(arr);
		//		}

		/**
		 * 5
		 * 1 2
		 * 1 3
		 * 3 4
		 * 3 5
		 *
		 */

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[][] roads = new int[n-1][2];
		for(int roads_i = 0; roads_i < n-1; roads_i++){
			for(int roads_j = 0; roads_j < 2; roads_j++){
				roads[roads_i][roads_j] = in.nextInt();
			}
		}
		int result = kingdomDivision(n, roads);
		System.out.println(result);
		in.close();

		// System.out.println(Math.pow(2,3) + 1 - 4);
	}

	static int kingdomDivision(int n, int[][] roads) {
		// Complete this function
		System.out.println(roads);
		return 0;
	}

}
