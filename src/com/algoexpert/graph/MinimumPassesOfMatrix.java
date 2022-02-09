package com.algoexpert.graph;

import java.util.ArrayList;

public class MinimumPassesOfMatrix {

	public int minimumPassesOfMatrix(int[][] matrix) {
		int passes = convertNegatives(matrix);
		if(!containsNegative(matrix)) {
			return passes -1;
		}
		return -1;
	}

	private int convertNegatives(int[][] matrix) {
		ArrayList<int[]> nextQueue = getAllPositivePositions(matrix);
		int passes = 0;

		while(nextQueue.size() > 0) {
			ArrayList<int[]> currentQueue = nextQueue;
			nextQueue = new ArrayList<>();
			while(currentQueue.size() > 0) {
				int[] vals = currentQueue.remove(0);

				ArrayList<int[]> adjacentList = getAdjacentPositions(vals[0], vals[1], matrix);
				for(int[] position: adjacentList) {
					int row = position[0];
					int col = position[1];
					int value = matrix[row][col];

					if(value < 0) {
						matrix[row][col] *= -1;
						nextQueue.add(new int[] { row, col});
					}
				}
			}
			passes++;
		}
		return passes;

	}

	private ArrayList<int[]> getAllPositivePositions(int[][] matrix) {
		ArrayList<int[]> list = new ArrayList<>();

		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				int value = matrix[row][col];
				if (value > 0) {
					list.add(new int[] { row, col });
				}
			}
		}
		return list;
	}

	public ArrayList<int[]> getAdjacentPositions(int row, int col, int[][] matrix) {
		ArrayList<int[]> list = new ArrayList<>();

		if (row > 0) {
			list.add(new int[] { row - 1, col });
		}

		if (row < matrix.length - 1) {
			list.add(new int[] { row + 1, col });
		}

		if (col > 0) {
			list.add(new int[] { row, col - 1 });
		}

		if (col < matrix[0].length - 1) {
			list.add(new int[] { row, col + 1 });
		}

		return list;
	}

	public boolean containsNegative(int[][] matrix) {
		for (int[] row : matrix) {
			for (int value : row) {
				if (value < 0) {
					return true;
				}
			}
		}
		return false;
	}

}
