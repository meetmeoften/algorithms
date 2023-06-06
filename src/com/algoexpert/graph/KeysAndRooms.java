package com.algoexpert.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KeysAndRooms {

	public boolean canVisitAllRooms(List<List<Integer>> rooms) {

		Queue<Integer> q = new LinkedList<>();
		int n = rooms.size();
		boolean[] visited = new boolean[n];

		visited[0] = true;

		for (int i : rooms.get(0)) {
			q.add(i);
			visited[i] = true;
		}

		while (!q.isEmpty()) {
			int temp = q.poll();

			for (int i : rooms.get(temp)) {
				if (visited[i] == false) {
					q.add(i);
					visited[i] = true;
				}
			}
		}

		for (int i = 0; i < visited.length; i++) {
			if (visited[i] == false) {
				return false;
			}
		}

		return true;
	}

	// DFS

	public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
		boolean[] vis = new boolean[rooms.size()];
		dfs(rooms, vis, 0);

		for (int i = 0; i < vis.length; i++) {
			if (vis[i] == false) {
				return false;
			}
		}
		return true;
	}

	public void dfs(List<List<Integer>> rooms, boolean[] vis, int s) {
		vis[s] = true;
		for (int i = 0; i < rooms.get(s).size(); i++) {
			if (vis[rooms.get(s).get(i)] == false) {
				dfs(rooms, vis, rooms.get(s).get(i));
			}
		}
	}

}
