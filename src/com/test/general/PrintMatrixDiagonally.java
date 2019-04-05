package com.test.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.util.Pair;

public class PrintMatrixDiagonally {

	public static void main(String[] args) {
		new PrintMatrixDiagonally();
	}

	public PrintMatrixDiagonally() {
		int[][] matrix = {
				{1,  2,  3,  4},
				{5,  6,  7,  8},
				{9,  10, 11, 12},
				{13, 14, 15, 16},
		};

		Map<Integer, List<Pair<Integer, Integer>>> map = printMatrixDiagonally(5, 5);
		int count = 0;
		for(Entry<Integer, List<Pair<Integer, Integer>>> entry : map.entrySet()) {
			List<Pair<Integer, Integer>> valueList = entry.getValue();
			for(Pair<Integer, Integer> value : valueList) {
				if(value.getKey().equals(1) && value.getValue().equals(1)) {
					count++;
				}
			}
		}
		System.out.println(count);

	}

	private Map<Integer, List<Pair<Integer, Integer>>> printMatrixDiagonally(int rowCount, int columnCount){


		Map<Integer, List<Pair<Integer, Integer>>> map = new HashMap<>();
		int key = 0;
		for (int r = 0; r < rowCount; r++) {
			List<Pair<Integer, Integer>> list = new ArrayList<>();
			for (int row = r, col = 0; row >= 0 && col < columnCount; row--, col++) {
				list.add(new Pair<Integer, Integer>(row+1, col+1));
			}
			map.put(key++, list);
		}

		for (int c = 1; c < columnCount; c++) {
			List<Pair<Integer, Integer>> list = new ArrayList<>();
			for (int row = rowCount-1, col = c; row >= 0 && col < columnCount; row--, col++) {
				list.add(new Pair<Integer, Integer>(row+1, col+1));
			}
			map.put(key++, list);
		}

		for (int r = 0; r < rowCount; r++) {
			List<Pair<Integer, Integer>> list = new ArrayList<>();
			for (int row = r, col = columnCount-1; row >= 0 && col >= 0; row--, col--) {
				list.add(new Pair<Integer, Integer>(row+1, col+1));
			}
			map.put(key++, list);
		}

		for (int c = columnCount-2; c >= 0; c--) {
			List<Pair<Integer, Integer>> list = new ArrayList<>();
			for (int row = rowCount-1, col = c; row >= 0 && col >= 0; row--, col--) {
				list.add(new Pair<Integer, Integer>(row+1, col+1));
			}
			map.put(key++, list);
		}
		return map;

	}
}
