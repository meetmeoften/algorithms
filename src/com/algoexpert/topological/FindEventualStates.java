package com.algoexpert.topological;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindEventualStates {

	public List<Integer> eventualSafeNodes(int[][] graph) {
		int v = graph.length;
		List<Integer> ans = new LinkedList<>();
		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < v; i++) {
			adj.add(new ArrayList<>());
		}
		int[] indeg = new int[v];
		for (int i = 0; i < v; i++) {
			for (int j : graph[i]) {
				adj.get(j).add(i);
				indeg[i]++;
			}
		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < v; i++) {
			if (indeg[i] == 0) {
				q.add(i);
			}
		}
		while (!q.isEmpty()) {
			int temp = q.poll();
			ans.add(temp);
			for (int j : adj.get(temp)) {
				indeg[j]--;
				if (indeg[j] == 0) {
					q.add(j);
				}
			}
		}
		Collections.sort(ans);
		return ans;
	}

	// -------------------

	public boolean dfs(int[][] graph, int i, boolean[] vis, boolean[] dfsvis, boolean[] check) {
		vis[i] = true;
		dfsvis[i] = true;
		check[i] = true;
		for (int j : graph[i]) {
			if (!vis[j]) {
				if (dfs(graph, j, vis, dfsvis, check)) {
					return true;
				}
			} else if (dfsvis[j]) {
				return true;
			}
		}
		dfsvis[i] = false;
		check[i] = false;
		return false;
	}

	public List<Integer> eventualSafeNodes2(int[][] graph) {
		int n = graph.length;
		List<Integer> l = new ArrayList<>();
		boolean[] vis = new boolean[n];
		boolean[] dfsvis = new boolean[n];
		boolean[] check = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (!vis[i]) {
				dfs(graph, i, vis, dfsvis, check);
			}
		}
		for (int i = 0; i < n; i++) {
			if (!check[i]) {
				l.add(i);
			}
		}
		return l;
	}

	public static void main(String[] args) {
		int[][] graph = { { 1, 2 }, { 2, 3 }, { 5 }, { 0 }, { 5 }, {}, {} };
		new FindEventualStates().eventualSafeNodes(graph);
	}

}
