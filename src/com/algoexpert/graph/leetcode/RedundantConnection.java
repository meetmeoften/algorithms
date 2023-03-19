package com.algoexpert.graph.leetcode;

public class RedundantConnection {

	public int[] findRedundantConnection2(int[][] edges) {
		int[] parents = new int[20000];

		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}

		for (int[] edge : edges) {
			int p1 = findParent(parents, edge[0]);
			int p2 = findParent(parents, edge[1]);
			if (p1  == p2) {
				return edge;
			} else {
				union(parents, p1, p2);
			}
		}

		return new int[2];
	}

	private static int findParent(int[] parents, int node) {
		if (parents[node] == node) {
			return parents[node];
		}
		parents[node] = findParent(parents, parents[node]);
		return parents[node];
	}

	private static void union(int[] parents, int p1, int p2) {
		parents[p2] = parents[p1];
	}

}
