package com.algoexpert.graph.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class AllPathCityZero {

	public int minReorder(int n, int[][] arr) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		ArrayList<ArrayList<Integer>> adj1 = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
			adj1.add(new ArrayList<>());
		}
		for (int i = 0; i < arr.length; i++) {
			int src = arr[i][0];
			int dest = arr[i][1];
			adj.get(src).add(dest);
			adj1.get(dest).add(src);
		}

		boolean[] isVisited = new boolean[n];
		isVisited[0] = true;
		return getMin(0, adj, adj1, isVisited);
	}

	private int getMin(int curNode, ArrayList<ArrayList<Integer>> adj, ArrayList<ArrayList<Integer>> adj2,
			boolean[] isVisited) {
		int count = 0;
		for (int ele : adj.get(curNode)) {
			if (!isVisited[ele]) {
				isVisited[ele] = true;
				count++;
				count += getMin(ele, adj, adj2, isVisited);
			}
		}
		for (int ref : adj2.get(curNode)) {
			if (!isVisited[ref]) {
				isVisited[ref] = true;
				count += getMin(ref, adj, adj2, isVisited);
			}
		}
		return count;

	}

	// ------------------------

	public static int minReorder2(int n, int[][] connections) {
		HashSet<String> set = new HashSet<>();
		ArrayList<Integer>[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int[] e : connections) {
			int u = e[0], v = e[1];
			graph[u].add(v);
			graph[v].add(u);
			set.add(u + "->" + v);
		}
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		int res = 0;
		boolean[] vis = new boolean[n];
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				int rem = q.remove();
				vis[rem] = true;
				for (int e : graph[rem]) {
					if (!vis[e]) {
						if (set.contains(rem + "->" + e)) {
							res++;
						}
						q.add(e);
					}
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int[][] connections = { { 0, 1 }, { 1, 3 }, { 2, 3 }, { 4, 0 }, { 4, 5 } };
		minReorder2(6, connections);

	}

}
