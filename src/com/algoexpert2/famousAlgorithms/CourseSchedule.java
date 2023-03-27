package com.algoexpert2.famousAlgorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CourseSchedule {

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		if (prerequisites == null || prerequisites.length == 0) {
			return true;
		}

		Map<Integer, Set<Integer>> graph = new HashMap<>();
		int[] dependants = new int[numCourses];

		for (int[] p : prerequisites) {
			graph.putIfAbsent(p[1], new HashSet<>());
			graph.get(p[1]).add(p[0]);

			dependants[p[0]]++;
		}

		Queue<Integer> q = new LinkedList<>();

		for (int i = 0; i < dependants.length; i++) {
			if (dependants[i] == 0) {
				q.offer(i);
			}
		}

		int count = 0;
		while (!q.isEmpty()) {
			int curr = q.poll();

			if (dependants[curr] == 0) {
				++count;
			}
			if (!graph.containsKey(curr)) {
				continue;
			}

			for (int neighbour : graph.get(curr)) {
				dependants[neighbour]--;
				if (dependants[neighbour] == 0) {
					q.offer(neighbour);
				}
			}
		}

		return count == numCourses;
	}

	public static void main(String[] args) {
		int[][] values = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
		System.out.println(new CourseSchedule().canFinish(4, values));
	}

}
