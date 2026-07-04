package com.algoexpert.topological;

import java.util.*;

public class ParallelCourses2 {

    public int minNumberOfSemesters(int numCourses, int[][] prerequisites, int k) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, List<Integer>> reverseGraph = new HashMap<>();
        int[] inDegrees = new int[numCourses + 1];

        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int pre = prereq[1];
            graph.putIfAbsent(pre, new ArrayList<>());
            graph.get(pre).add(course);

            graph.putIfAbsent(course, new ArrayList<>());
            graph.get(course).add(pre);
            inDegrees[course]++;
        }

        // Queue for courses with no prerequisites
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= numCourses; i++) {
            if (inDegrees[i] == 0 && (graph.containsKey(i) || reverseGraph.containsKey(i))) {
                queue.offer(i);
            }
        }

        int semesters = 0;

        // Process courses
        while (!queue.isEmpty()) {
            int size = queue.size();
            int count = Math.min(size, k); // Take up to k courses

            // Process the courses for the current semester
            for (int i = 0; i < count; i++) {
                int course = queue.poll();
                if (graph.containsKey(course)) {
                    for (int next : graph.get(course)) {
                        inDegrees[next]--;
                        if (inDegrees[next] == 0) {
                            queue.offer(next);
                        }
                    }
                }
            }
            semesters++;
        }
        return semesters;
    }

    public static void main(String[] args) {
        ParallelCourses2 obj = new ParallelCourses2();
        int numCourses = 4;
        //int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int[][] prerequisites = {{2, 1},{2,4}};
        int k = 2;
        System.out.println(obj.minNumberOfSemesters(numCourses, prerequisites, k));
    }
}
