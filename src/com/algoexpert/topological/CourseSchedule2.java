package com.algoexpert.topological;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseSchedule2 {

	public static int[] findOrder(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
		int[] indegree = new int[numCourses];
		int[] topologicalOrder = new int[numCourses];

		// creating the adjlist
		for (int i = 0; i < prerequisites.length; i++) {
			int post = prerequisites[i][0];
			int pre = prerequisites[i][1];
			List<Integer> lst = adjList.getOrDefault(pre, new ArrayList<Integer>());
			lst.add(post);
			adjList.put(pre, lst);

			indegree[post] += 1;
		}

		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				q.add(i);
			}
		}

		int i = 0;
		while (!q.isEmpty()) {
			int node = q.remove();
			topologicalOrder[i++] = node;

			if (adjList.containsKey(node)) {
				for (Integer neighbor : adjList.get(node)) {
					indegree[neighbor]--;

					if (indegree[neighbor] == 0) {
						q.add(neighbor);
					}
				}
			}
		}

		if (i == numCourses) {
			return topologicalOrder;
		}

		return new int[0];
	}

	public static void main(String[] args) {
		int[][] values = { { 1, 0 } };
		int[] result = findOrder(2, values);
		System.out.println(result);
	}

}
