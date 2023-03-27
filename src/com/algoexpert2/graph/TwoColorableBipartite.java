package com.algoexpert2.graph;

import java.util.Stack;

public class TwoColorableBipartite {

	public boolean twoColorable(int[][] edges) {
		int[] colors = new int[edges.length];
		colors[0] = 1;
		Stack<Integer> stack = new Stack<>();

		stack.push(0);

		while (stack.size() > 0) {
			int node = stack.pop();
			for (int connection : edges[node]) {
				if (colors[connection] == 0) {
					if(colors[node] == 1) {
						colors[connection] = 2;
					} else {
						colors[connection] = 1;
					}
					//colors[connection] = colors[node] == 1 ? 2 : 1;
					System.out.println(connection);
					stack.push(connection);
				} else if (colors[connection] == colors[node]) {
					return false;
				}
			}

		}
		return true;
	}

	public static void main(String[] args) {
		// int[][] input = new int[][] { { 1 }, { 0 } };
		int[][] input = new int[][] { { 1, 3 }, { 0, 2 },  { 1, 3 }, { 0, 2 }};
		var expected = true;
		var actual = new TwoColorableBipartite().twoColorable(input);
	}
}