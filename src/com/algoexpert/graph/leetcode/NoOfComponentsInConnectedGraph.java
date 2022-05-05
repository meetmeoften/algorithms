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
		int[][] edges = new int[][] {  { 3, 5 }, { 3, 4 }, {3, 5}, { 0, 1 }, { 1, 2 }};
		System.out.println(countComponents(6, edges));
	}


	public int recur(int[] h, int pos, int x, int y, int n) {
		if (pos >= n) {
			return 0;
		}
		int v1 = 0, v2 = 0, v3 = 0;
		if (x >= h[pos]) {
			v1++;
			v1 += recur(h, pos + 1, x - h[pos], y, n);
		}
		if (y >= h[pos]) {
			v2++;
			v2 += recur(h, pos + 1, x, y - h[pos], n);
		}
		v3 = recur(h, pos + 1, x, y, n);
		return Math.max(v1, Math.max(v2, v3));
	}

}
