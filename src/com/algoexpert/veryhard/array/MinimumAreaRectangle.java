package com.algoexpert.veryhard.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class MinimumAreaRectangle {

	public int minimumAreaRectangle(int[][] points) {
		// Write your code here.
		HashMap<Integer, int[]> columns = initializeColumns(points);
		int minimumAreaFound = Integer.MAX_VALUE;

		HashMap<String, Integer> edgesParallelToYAxis = new HashMap<String, Integer>();

		ArrayList<Integer> sortedColumns = new ArrayList<Integer>(columns.keySet());
		Collections.sort(sortedColumns);

		for (Integer x : sortedColumns) {
			int[] yValuesInCurrentColumn = columns.get(x);
			Arrays.sort(yValuesInCurrentColumn);

			for (int currentIdx = 0; currentIdx < yValuesInCurrentColumn.length; currentIdx++) {
				int y2 = yValuesInCurrentColumn[currentIdx];
				for (int previousIdx = 0; previousIdx < currentIdx; previousIdx++) {
					int y1 = yValuesInCurrentColumn[previousIdx];
					String pointString = String.valueOf(y1) + ":" + String.valueOf(y2);

					if (edgesParallelToYAxis.containsKey(pointString)) {
						int currentArea = (x - edgesParallelToYAxis.get(pointString)) * (y2 - y1);
						minimumAreaFound = Math.min(minimumAreaFound, currentArea);
					}
					edgesParallelToYAxis.put(pointString, x);
				}
			}
		}

		return (minimumAreaFound != Integer.MAX_VALUE) ? minimumAreaFound : 0;
	}

	public HashMap<Integer, int[]> initializeColumns(int[][] points) {
		HashMap<Integer, int[]> columns = new HashMap<>();

		for (int[] point : points) {
			int x = point[0];
			int y = point[1];

			if (!columns.containsKey(x)) {
				columns.put(x, new int[] {});
			}
			int[] column = columns.get(x);
			int[] newColumn = new int[column.length + 1];

			for (int i = 0; i < column.length; i++) {
				newColumn[i] = column[i];
			}
			newColumn[column.length] = y;
			columns.put(x, newColumn);
		}
		return columns;
	}

	public static void main(String[] args) {
		int[][] input =
				new int[][] {
			{1, 5},
			{5, 1},
			{4, 2},
			{2, 4},
			{2, 2},
			{1, 2},
			{4, 5},
			{2, 5},
			{-1, -2}
		};
		int expected = 3;
		var actual = new MinimumAreaRectangle().minimumAreaRectangle(input);
	}

}
