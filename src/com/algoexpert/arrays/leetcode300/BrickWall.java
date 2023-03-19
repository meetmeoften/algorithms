package com.algoexpert.arrays.leetcode300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BrickWall {

	public static int leastBricks(List<List<Integer>> wall) {
		Map<Integer, Integer> map = new HashMap<>();
		int maxGaps = 0;

		for (List<Integer> row : wall) {
			int pos = 0;
			for (int i = 0; i < row.size() - 1; i++) {
				pos += row.get(i);
				map.put(pos, map.getOrDefault(pos, 0) + 1);
				maxGaps = Math.max(maxGaps, map.get(pos));
			}
		}

		return wall.size() - maxGaps;
	}


	public static void main(String[] args) {
		Integer[][] wall = {{1,2,2,1},{3,1,2},{1,3,2},{2,4},{3,1,2},{1,3,1,1}};


		List<List<Integer>> resList = new ArrayList<>();
		for(Integer[] rows : wall) {
			resList.add(Arrays.stream(rows).collect(Collectors.toList()));
		}
		leastBricks(resList);
	}

}
