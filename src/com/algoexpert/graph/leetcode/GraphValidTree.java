package com.algoexpert.graph.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	private Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

	public boolean validTree2(int n, int[][] edges) {
		if (n == 0 || n == 1) {
			return true;
		}

		if (edges.length == 0) {
			return false;
		}

		for (var edge : edges) {
			var node1 = edge[0];
			var node2 = edge[1];
			adjacencyList.putIfAbsent(node1, new ArrayList<>());
			adjacencyList.putIfAbsent(node2, new ArrayList<>());
			adjacencyList.get(node1).add(node2);
			adjacencyList.get(node2).add(node1);
		}

		Set<Integer> visited = new HashSet<>();

		return (depthFirstSearch(edges[0][0], -1, visited) && visited.size() == n);
	}

	private boolean depthFirstSearch(int node, int previous, Set<Integer> visited) {
		if (visited.contains(node)) {
			return false;
		}

		visited.add(node);

		for (var neighbor : adjacencyList.get(node)) {
			if (neighbor == previous) {
				continue;
			}

			if (!depthFirstSearch(neighbor, node, visited)) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		int[][] edges = new int[][] { { 0, 1 }, { 1, 2 }, { 3, 4 } };
		int[][] edges2 = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 1, 3 }, { 1, 4 } };
		System.out.println(validTree(5, edges2));
	}
}
