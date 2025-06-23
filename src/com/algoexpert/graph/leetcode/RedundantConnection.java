package com.algoexpert.graph.leetcode;

import java.util.HashSet;
import java.util.Set;

public class RedundantConnection {

	public static int[] findRedundantConnection2(int[][] edges) {
		int[] parents = new int[5];

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

	// --------------

	public static int countCompleteComponents(int n, int[][] edges) {
		UnionFind uf = new UnionFind(n);

		for (int i = 0; i < edges.length; i++) {
			int p1 = uf.findParent(edges[i][0]);
			int p2 = uf.findParent(edges[i][1]);
			if (p1 != p2) {
				uf.union(p1, p2);

			}
		}
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < n; i++) {
			int p = uf.findParent(i);
			System.out.println("node " + i + " " + p);
			set.add(p);
		}
		return set.size();
	}

	static class UnionFind {

		private int[] parents;

		public UnionFind(int n) {
			parents = new int[n];
			for (int i = 0; i < n; i++) {
				parents[i] = i;
			}
		}

		public Integer findParent(int node) {
			if (parents[node] == node) {
				return node;
			}
			return findParent(parents[node]);
		}

		public void union(int node1, int node2) {
			parents[node1] = parents[node2];
		}

	}

	public static void main(String[] args) {
		// int[][] edges = { { 1, 2 }, { 1, 3 }, { 2, 3 } };
		// findRedundantConnection2(edges);
		int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 3, 4 }, { 3, 5 } };
		countCompleteComponents(6, edges);

	}

}
