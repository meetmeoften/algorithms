package com.google.graph;

import java.util.*;

public class MinimumScoreBetweenTwoCities {
    private int minScore = Integer.MAX_VALUE;
    private boolean[] visited;
    private Map<Integer, List<int[]>> graph = new HashMap<>();

    public int minScore(int n, int[][] roads) {
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            int dist = road[2];
            graph.get(a).add(new int[]{b, dist});
            graph.get(b).add(new int[]{a, dist});
        }
        dfs(1);
        return minScore;
    }

    private void dfs(int city) {
        visited[city] = true;
        for (int[] neighbour : graph.get(city)) {
            minScore = Math.min(minScore, neighbour[1]);
            if (!visited[neighbour[0]]) {
                dfs(neighbour[0]);
            }
        }
    }


    public int minScore2(int n, int[][] roads) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] road : roads) {
            graph.get(road[0]).add(new int[]{road[1], road[2]});
            graph.get(road[1]).add(new int[]{road[0], road[2]});
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        int minScore = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int city = queue.poll();
            for (int[] neighbor : graph.get(city)) {
                minScore = Math.min(minScore, neighbor[1]);
                if (!visited[neighbor[0]]) {
                    visited[neighbor[0]] = true;
                    queue.offer(neighbor[0]);
                }
            }
        }
        return minScore;
    }
}
