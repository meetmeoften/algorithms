package com.google.graph;

import java.util.*;

public class ShortestCycleInGraph {

    public int findShortestCycle(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        int ans = Integer.MAX_VALUE;

        for (int start = 0; start < n; start++) {
            int[] dist = new int[n];
            Arrays.fill(dist, -1);
            int[] parent = new int[n];

            Queue<Integer> q = new LinkedList<>();
            q.offer(start);
            dist[start] = 0;
            parent[start] = -1;

            while (!q.isEmpty()) {
                int u = q.poll();

                for (int v : graph[u]) {
                    if (dist[v] == -1) {
                        dist[v] = dist[u] + 1;
                        parent[v] = u;
                        q.offer(v);
                    }
                    // Found a cycle
                    else if (parent[u] != v) {
                        ans = Math.min(ans, dist[u] + dist[v] + 1);
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        int[][] edges = {
                {0, 1},
                {1, 2},
                {2, 0},
                {3, 4},
                {4, 5},
                {5, 6},
                {6, 3},
        };

        // 0->1 ->2-0          3-4-5-6-3
        ShortestCycleInGraph solution = new ShortestCycleInGraph();
        int result = solution.findShortestCycle(7, edges);
        System.out.println(result);
    }
}
