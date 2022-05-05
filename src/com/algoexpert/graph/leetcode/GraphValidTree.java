package com.algoexpert.graph.leetcode;

public class GraphValidTree {

	public static boolean validTree(int n, int[][] edges) {
		int[] parents = new int[n];

		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}

		for (int[] edge : edges) {
			int p1 = findParent(parents, edge[0]);
			int p2 = findParent(parents, edge[1]);

			if (p1 == p2) {
				return false;
			}

			union(parents, p1, p2);
			--n;
		}

		return n == 1;
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


	public int[] findRedundantConnection(int[][] edges) {
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
				parents[p1] = p2;
			}
		}

		return new int[2];
	}

	private int find(int[] parent, int f) {
		if (f != parent[f]) {
			parent[f] = find(parent, parent[f]);
		}
		return parent[f];
	}

	public static void main(String[] args) {
		int[][] edges = new int[][] { { 0, 1 }, { 1, 2 }, { 3, 4 } };
		int[][] edges2 = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 1, 4 } };
		System.out.println(validTree(5, edges2));
	}
}
