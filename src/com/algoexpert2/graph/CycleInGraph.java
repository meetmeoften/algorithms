package com.algoexpert2.graph;

import java.util.Arrays;

public class CycleInGraph {

	public boolean cycleInGraph(int[][] edges) {
		// Write your code here.
		int numberOfNodes = edges.length;

		boolean[] visited = new boolean[numberOfNodes];
		boolean[] currentlyInStack = new boolean[numberOfNodes];

		Arrays.fill(visited, false);
		Arrays.fill(currentlyInStack, false);

		for (int node = 0; node < numberOfNodes; node++) {
			if (visited[node]) {
				continue;
			}
			boolean containsCycle = isNodeInCycle(node, edges, visited, currentlyInStack);
			if (containsCycle) {
				return true;
			}
		}
		return false;
	}

	public boolean isNodeInCycle(int node, int[][] edges, boolean[] visited, boolean[] currentlyInStack) {
		visited[node] = true;
		currentlyInStack[node] = true;

		boolean containsCycle = false;
		int[] neighbours = edges[node];
		for (int neighbour : neighbours) {
			if (!visited[neighbour]) {
				containsCycle = isNodeInCycle(neighbour, edges, visited, currentlyInStack);
			}

			if (containsCycle) {
				return true;
			} else if (currentlyInStack[neighbour]) {
				return true;
			}
		}
		currentlyInStack[node] = false;
		return false;
	}

	public boolean cycleInGraph2(int[][] edges) {

		int n = edges.length;
		boolean[] visited = new boolean[edges.length];
		boolean[] currentlyInStack = new boolean[edges.length];

		for(int i= 0; i < n; i++) {
			if(visited[i]) {
				continue;
			}
			if(helper(i, edges, visited, currentlyInStack)) {
				return true;
			}
		}
		return false;


	}

	public boolean helper(int node, int[][] edges, boolean[] visited, boolean[] currentlyInStack) {

		visited[node] = true;
		currentlyInStack[node] = true;
		boolean containsCycle = false;
		int[] neighbours = edges[node];

		for(int neighbour: neighbours) {
			if(!visited[neighbour]) {
				containsCycle = helper(neighbour, edges, visited, currentlyInStack);
			}

			if(containsCycle) {
				return true;
			}

			else if(currentlyInStack[neighbour]) {
				return true;
			}
		}
		currentlyInStack[node] = false;
		return false;
	}


	public static void main(String[] args) {
		int[][] input =
				new int[][] {
			{1, 3},
			{2, 3, 4},
			{0},
			{},
			{2, 5},
			{}
		};
		boolean expected = true;
		var actual = new CycleInGraph().cycleInGraph(input);
	}

}
