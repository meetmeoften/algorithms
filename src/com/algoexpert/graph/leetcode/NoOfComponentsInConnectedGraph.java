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


	//--------------------------------------------------------------------

	private int[] parent;
	private int[] rank;

	public int countComponents2(int n, int[][] edges) {
		parent = new int[n];
		rank = new int[n];

		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 1;
		}

		int result = n;
		for (int i = 0; i < edges.length; i++) {
			if (union(edges[i][0], edges[i][1]) == 1) {
				result--;
			}
		}

		return result;
	}

	private int find(int node) {
		int result = node;

		while (parent[result] != result) {
			parent[result] = parent[parent[result]];
			result = parent[result];
		}

		return result;
	}

	private int union(int n1, int n2) {
		int p1 = this.find(n1);
		int p2 = this.find(n2);

		if (p1 == p2) {
			return 0;
		}

		if (rank[p2] > rank[p1]) {
			parent[p1] = p2;
			rank[p2] += rank[p1];
		} else {
			parent[p2] = p1;
			rank[p1] += rank[p2];
		}

		return 1;
	}



	public static void main(String[] args) {
		int[][] edges = new int[][] {  { 3, 5 }, { 3, 4 }, { 0, 1 }, { 1, 2 }};
		System.out.println(countComponents(6, edges));
	}

}
