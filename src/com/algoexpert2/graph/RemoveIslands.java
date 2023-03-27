package com.algoexpert2.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RemoveIslands {

	// Remove islands
	public int[][] removeIslands(int[][] matrix) {
		boolean[][] onesConnectedToBorder = new boolean[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			onesConnectedToBorder[i][matrix[0].length - 1] = false;
		}

		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {

				boolean rowBorder = row == 0 || row == matrix.length - 1;
				boolean colBorder = col == 0 || col == matrix[0].length - 1;
				boolean isBorder = rowBorder || colBorder;

				if (!isBorder) {
					continue;
				}

				if (matrix[row][col] != 1) {
					continue;
				}
				findOnesConnectedToBorder(matrix, row, col, onesConnectedToBorder);
			}
		}
		for (int row = 1; row < matrix.length - 1; row++) {
			for (int col = 1; col < matrix[row].length - 1; col++) {
				if (onesConnectedToBorder[row][col]) {
					continue;
				}
				matrix[row][col] = 0;
			}
		}
		return matrix;
	}

	private void findOnesConnectedToBorder(int[][] matrix, int row, int col, boolean[][] onesConnectedToBorder) {
		// TODO Auto-generated method stub
		Stack<Integer[]> stack = new Stack<>();
		stack.push(new Integer[] { row, col });

		while (stack.size() > 0) {
			Integer[] currentPosition = stack.pop();
			int i = currentPosition[0];
			int j = currentPosition[1];

			if (onesConnectedToBorder[i][j]) {
				continue;
			}
			onesConnectedToBorder[i][j] = true;
			if (matrix[i][j] != 1) {
				continue;
			}

			List<Integer[]> unvisitedNeighbours = getUnvisitedNeighbours(i, j, matrix, onesConnectedToBorder);
			for (Integer[] neighbour : unvisitedNeighbours) {
				stack.add(neighbour);
			}
		}
	}

	private static List<Integer[]> getUnvisitedNeighbours(int i, int j, int[][] matrix, boolean[][] visited) {
		// TODO Auto-generated method stub
		List<Integer[]> unvisitedNeighbours = new ArrayList<>();

		if (i > 0 && !visited[i - 1][j]) { // check top
			unvisitedNeighbours.add(new Integer[] { i - 1, j });
		}

		if (i < matrix.length - 1 && !visited[i + 1][j]) { // check below
			unvisitedNeighbours.add(new Integer[] { i + 1, j });
		}

		if (j > 0 && !visited[i][j - 1]) { // check left
			unvisitedNeighbours.add(new Integer[] { i, j - 1 });
		}

		if (j < matrix[0].length - 1 && !visited[i][j + 1]) { // check right
			unvisitedNeighbours.add(new Integer[] { i, j + 1 });
		}
		return unvisitedNeighbours;
	}

	// ---------------

	public int[][] removeIslands2(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if(i == 0 || j == 0 || i == matrix.length-1 || j == matrix[0].length-1) {
					if (matrix[i][j] == 1) {
						int count = 0;
						dfs(matrix, i, j, count);
					}
				}
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 2) {
					matrix[i][j] = 1;
				} else {
					matrix[i][j] = 0;
				}
			}
		}

		return matrix;
	}

	private static void dfs(int[][] matrix, int x, int y, int count) {
		if (x < 0 || y < 0 || x > matrix.length - 1 || y > matrix[0].length - 1 || matrix[x][y] == 0 || matrix[x][y] == 2) {
			return;
		}
		matrix[x][y] = 2;
		count++;
		dfs(matrix, x + 1, y, count);
		dfs(matrix, x - 1, y, count);
		dfs(matrix, x, y + 1, count);
		dfs(matrix, x, y - 1, count);
	}

	// -----------------

	public static void main(String[] args) {
		int[][] input =
				new int[][] {
			{1, 0, 0, 0, 0, 0},
			{0, 1, 0, 1, 1, 1},
			{0, 0, 1, 0, 1, 0},
			{1, 1, 0, 0, 1, 0},
			{1, 0, 1, 1, 0, 0},
			{1, 0, 0, 0, 0, 1},
		};
		int[][] expected =
				new int[][] {
			{1, 0, 0, 0, 0, 0},
			{0, 0, 0, 1, 1, 1},
			{0, 0, 0, 0, 1, 0},
			{1, 1, 0, 0, 1, 0},
			{1, 0, 0, 0, 0, 0},
			{1, 0, 0, 0, 0, 1},
		};
		int[][] actual = new RemoveIslands().removeIslands(input);
		//		for (int i = 0; i < actual.length; i++) {
		//			for (int j = 0; j < actual[i].length; j++) {
		//				Utils.assertTrue(actual[i][j] == expected[i][j]);
		//			}
		//		}
	}

}
