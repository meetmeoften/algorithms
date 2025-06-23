package com.algoexpert.graph.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CountCompleteComponents {

	public int countCompleteComponents(int n, int[][] edges) {
		// Adjacency lists for each vertex
		List<Integer>[] graph = new ArrayList[n];
		// Map to store frequency of each unique adjacency list
		Map<List<Integer>, Integer> componentFreq = new HashMap<>();

		// Initialize adjacency lists with self-loops
		for (int vertex = 0; vertex < n; vertex++) {
			graph[vertex] = new ArrayList<>();
			graph[vertex].add(vertex);
		}

		// Build adjacency lists from edges
		for (int[] edge : edges) {
			graph[edge[0]].add(edge[1]);
			graph[edge[1]].add(edge[0]);
		}

		// Count frequency of each unique adjacency pattern
		for (int vertex = 0; vertex < n; vertex++) {
			List<Integer> neighbors = graph[vertex];
			Collections.sort(neighbors);
			componentFreq.put(neighbors, componentFreq.getOrDefault(neighbors, 0) + 1);
		}

		// Count complete components where size equals frequency
		int completeCount = 0;
		for (Map.Entry<List<Integer>, Integer> entry : componentFreq.entrySet()) {
			if (entry.getKey().size() == entry.getValue()) {
				completeCount++;
			}
		}

		return completeCount;
	}

	public int countCompleteComponents2(int n, int[][] edges) {
		// Adjacency lists for each vertex
		List<Integer>[] graph = new ArrayList[n];

		// Initialize empty adjacency lists
		for (int vertex = 0; vertex < n; vertex++) {
			graph[vertex] = new ArrayList<>();
		}

		// Build adjacency lists from edges
		for (int[] edge : edges) {
			graph[edge[0]].add(edge[1]);
			graph[edge[1]].add(edge[0]);
		}

		int completeCount = 0;
		Set<Integer> visited = new HashSet<>();

		// Process each unvisited vertex
		for (int vertex = 0; vertex < n; vertex++) {
			if (visited.contains(vertex)) {
				continue;
			}

			// arr[0] = vertices count, arr[1] = total edges count
			int[] componentInfo = new int[2];
			dfs(vertex, graph, visited, componentInfo);

			// Check if component is complete - edges should be vertices * (vertices-1)
			if (componentInfo[0] * (componentInfo[0] - 1) == componentInfo[1]) {
				completeCount++;
			}
		}
		return completeCount;
	}

	private void dfs(int curr, List<Integer>[] graph, Set<Integer> visited, int[] componentInfo) {
		visited.add(curr);
		componentInfo[0]++; // Increment vertex count
		componentInfo[1] += graph[curr].size(); // Add edges from current vertex

		// Explore unvisited neighbors
		for (int next : graph[curr]) {
			if (!visited.contains(next)) {
				dfs(next, graph, visited, componentInfo);
			}
		}
	}

	public static void main(String[] args) {
		CountCompleteComponents ccc = new CountCompleteComponents();
		int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 3, 4 } };
		ccc.countCompleteComponents2(6, edges);
	}

}
