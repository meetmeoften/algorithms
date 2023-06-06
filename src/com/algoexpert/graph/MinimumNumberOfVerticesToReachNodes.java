package com.algoexpert.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MinimumNumberOfVerticesToReachNodes {

	public static List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
		int indegree[] = new int[n];
		for (int i = 0; i < edges.size(); i++) {
			indegree[edges.get(i).get(1)]++;
		}
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (indegree[i] == 0) {
				res.add(i);
			}
		}
		return res;
	}

	public static void main(String[] args) {

		Integer[][] edges = { { 0, 1 }, { 0, 2 }, { 2, 5 }, { 3, 4 }, { 4, 2 } };

		List<List<Integer>> resList = new ArrayList<>();
		for(Integer[] rows : edges) {
			resList.add(Arrays.stream(rows).collect(Collectors.toList()));
		}

		findSmallestSetOfVertices(6, resList);

	}

}
