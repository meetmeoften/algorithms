package com.algoexpert.graph.leetcode;

import com.algoexpert.graph.advanced2.UnionFind;

public class RedundantConnection {

	public static int[] findRedundantConnection2(int[][] edges) {
		int[] parents = new int[6];

		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}

		for (int[] edge : edges) {
			int p1 = findParent(parents, edge[0]);
			int p2 = findParent(parents, edge[1]);
			if (p1 == p2) {
				return edge;
			} else {
				union(parents, p1, p2);
			}
		}

		return new int[2];
	}

	public static int[] findRedundantConnection(int[][] edges) {
		UnionFind uf = new UnionFind(edges.length);

		for (int[] edge : edges) {
			int p1 = uf.find(edge[0]);
			int p2 = uf.find(edge[1]);
			if (p1 == p2) {
				uf.connected(p1, p2);
				return edge;
			} else {
				uf.union(p1, p2);
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

	public static void main(String[] args) {
		int[][] edges = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 4 }, {1, 5} };
		findRedundantConnection(edges);
	}

}
