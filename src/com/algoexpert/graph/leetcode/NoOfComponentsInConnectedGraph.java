package com.algoexpert.graph.leetcode;

public class NoOfComponentsInConnectedGraph {

	public static int countComponents(int n, int[][] edges) {
		int[] parents = new int[n];

		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}

		int result = 0;

		for (int[] edge : edges) {
			int p1 = find(parents, edge[0]);
			int p2 = find(parents, edge[1]);

			if (p1 != p2) {
				++result;
				union(parents, p1, p2);
			}
		}

		return n - result;
	}

	private static int find(int[] parents, int node) {
		if (parents[node] == node) {
			return parents[node];
		}
		parents[node] = find(parents, parents[node]);
		return parents[node];
	}

	private static void union(int[] parents, int p1, int p2) {
		parents[p1] = p2;
	}

	public static void main(String[] args) {
		int[][] edges = new int[][] { { 0, 1 }, { 0, 2 }, { 3, 5 }, { 3, 4 }, { 4, 5 }};
		System.out.println(countComponents(6, edges));
	}

}
