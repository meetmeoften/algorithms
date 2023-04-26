package com.neetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Shift2DGrid {

	public static List<List<Integer>> shiftGrid(int[][] grid, int k) {

		int row = grid.length, col = grid[0].length;
		int[][] temp = grid;

		while (k-- > 0) {
			// init for each loop
			temp = new int[row][col];

			// 1. shift right
			for (int i = 0; i < row; i++) {
				for (int j = 1; j < col; j++) {
					temp[i][j] = grid[i][j - 1];
				}
			}

			// 2. move last one in this row to the next row
			for (int i = 1; i < row; i++) {
				temp[i][0] = grid[i - 1][col - 1];
			}

			// 3. move the last one to the first place
			temp[0][0] = grid[row - 1][col - 1];

			// repeat
			grid = temp;
		}

		return (List) Arrays.asList(temp);
	}

	// --------------------------

	public static List<List<Integer>> shiftGrid2(int[][] grid, int k) {
		int m = grid.length, n = grid[0].length;
		List<List<Integer>> result = new ArrayList<>(m);
		ArrayList<Integer> al = new ArrayList<>();

		for (int[] row : grid) {
			for (int element : row) {
				al.add(element);
			}
		}

		// ROTATING THE LAST ELEMENTS
		while (k-- > 0) {
			al.add(0, al.remove(al.size() - 1));
		}

		int index = 0;
		for (int i = 0; i < m; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				temp.add(al.get(index++));
			}
			result.add(temp);
		}
		return result;
	}

	public static void main(String[] args) {
		int[][] grid = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int k = 1;
		shiftGrid(grid, k);
		shiftGrid2(grid, k);
	}

}
