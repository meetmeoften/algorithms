package com.algoexpert.graph;

import java.util.*;

public class LargestColorValueInDirectedGraph {

    static class Solution {
        public int largestPathValue(String colors, int[][] edges) {
            int n = colors.length();
            List<List<Integer>> graph = new ArrayList<>();
            int[] indegree = new int[n];
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                graph.get(edge[0]).add(edge[1]);
                indegree[edge[1]]++;
            }

            // dp[node][color] = max count of that color on any path ending at node
            int[][] dp = new int[n][26];

            // Queue for nodes with indegree 0 (topological order)
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (indegree[i] == 0) {
                    queue.offer(i);
                    dp[i][colors.charAt(i) - 'a'] = 1;
                }
            }

            int visited = 0;
            int maxColorValue = 0;

            while (!queue.isEmpty()) {
                int node = queue.poll();
                visited++;
                for (int i = 0; i < 26; i++) {
                    maxColorValue = Math.max(maxColorValue, dp[node][i]);
                }

                for (int neighbor : graph.get(node)) {
                    for (int c = 0; c < 26; c++) {
                        int currentCount = dp[node][c] + (c == colors.charAt(neighbor) - 'a' ? 1 : 0);
                        dp[neighbor][c] = Math.max(dp[neighbor][c], currentCount);
                    }
                    indegree[neighbor]--;
                    if (indegree[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }

            // If not all nodes were visited, there's a cycle
            return visited == n ? maxColorValue : -1;
        }
    }

    public static void main(String[] args) {
        String colors = "abaca";
        int[][] edges = {
                {0, 1},
                {0, 2},
                {2, 3},
                {3, 4}
        };

        Solution solution = new Solution();
        int result = solution.largestPathValue(colors, edges);
        System.out.println("Largest color value in the graph: " + result);
    }
}

