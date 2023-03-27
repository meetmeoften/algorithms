package com.algoexpert2.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class RiverSizes {

	public static List<Integer> riverSizes(int[][] matrix) {
		List<Integer> sizes = new ArrayList<>();
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (visited[i][j]) {
					continue;
				}
				traverseNode(i, j, matrix, visited, sizes);
			}
		}
		return sizes;

	}

	private static void traverseNode(int row, int col, int[][] matrix, boolean[][] visited, List<Integer> sizes) {
		// TODO Auto-generated method stub
		int currentRiverSize = 0;
		Stack<Integer[]> stack = new Stack<>();
		stack.push(new Integer[] { row, col });

		while (!stack.isEmpty()) {
			Integer[] currentNode = stack.pop();
			int i = currentNode[0];
			int j = currentNode[1];

			if (visited[i][j]) {
				continue;
			}
			visited[i][j] = true;
			if (matrix[i][j] == 0) {
				continue;
			}
			currentRiverSize++;
			List<Integer[]> unvisitedNeighbours = getUnvisitedNeighbours(i, j, matrix, visited);
			for (Integer[] neighbour : unvisitedNeighbours) {
				stack.add(neighbour);
			}
		}
		if (currentRiverSize > 0) {
			sizes.add(currentRiverSize);
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

	// -----------

	public static List<Integer> riverSizes2(int[][] matrix) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 1) {
					int count = 0;
					dfs(matrix, i, j, count);
					list.add(count);
				}
			}
		}
		return list;
	}

	private static void dfs(int[][] matrix, int x, int y, int count) {
		if (x < 0 || y < 0 || x > matrix.length - 1 || y > matrix[0].length - 1 || matrix[x][y] == 0) {
			return;
		}
		matrix[x][y] = 0;
		count++;
		dfs(matrix, x + 1, y, count);
		dfs(matrix, x - 1, y, count);
		dfs(matrix, x, y + 1, count);
		dfs(matrix, x, y - 1, count);
	}

	public static void main(String[] args) {
		int[][] input = { { 1, 0, 0, 1, 0 }, { 1, 0, 1, 0, 0 }, { 0, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1 },
				{ 1, 0, 1, 1, 0 }, };
		int[] expected = { 1, 2, 2, 2, 5 };
		List<Integer> output = riverSizes(input);
		Collections.sort(output);
	}
}
