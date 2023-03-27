package com.algoexpert.topological;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CourseSchedule4 {

	public List<Boolean> checkIfPrerequisite(int numCourses, int[][] preReq, int[][] queries) {
		int[] inDegree = new int[numCourses];
		Map<Integer, Set<Integer>> graph = new HashMap<>();

		for (int i = 0; i < preReq.length; i++) {
			int independent = preReq[i][0];
			int dependent = preReq[i][1];
			inDegree[dependent]++;
			graph.putIfAbsent(independent, new HashSet<>());
			graph.get(independent).add(dependent);
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}

		boolean[][] dependency = new boolean[numCourses][numCourses];
		for (int i = 0; i < numCourses; i++) {
			dependency[i][i] = true;
		}

		while (!queue.isEmpty()) {
			int node = queue.poll();
			if (graph.get(node) == null) {
				continue;
			}
			for (Integer child : graph.get(node)) {
				inDegree[child]--;
				if (inDegree[child] == 0) {
					queue.add(child);
				}
				for (int i = 0; i < numCourses; i++) {
					dependency[child][i] |= dependency[node][i]; // bitwise OR operator
				}
			}
		}

		List<Boolean> queriesAns = new ArrayList<>();
		for (int i = 0; i < queries.length; i++) {
			int independent = queries[i][0];
			int dependent = queries[i][1];
			queriesAns.add(dependency[dependent][independent]);
		}
		return queriesAns;

	}

	public static void main(String[] args) {
		int[][] values = { { 0, 1 }, { 1, 0 } };
		int[][] preReq = { { 1, 0 } };
		System.out.println(new CourseSchedule4().checkIfPrerequisite(2, preReq, values));
	}

}
