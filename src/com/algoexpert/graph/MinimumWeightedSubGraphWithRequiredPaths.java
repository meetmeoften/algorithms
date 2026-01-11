package com.algoexpert.graph;

import java.util.*;

public class MinimumWeightedSubGraphWithRequiredPaths {


    private static final long INF = Long.MAX_VALUE / 4;

    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        List<int[]>[] graph = new ArrayList[n];
        List<int[]>[] reverseGraph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            reverseGraph[e[1]].add(new int[]{e[0], e[2]});
        }

        long[] dist1 = dijkstra(src1, graph, n);
        long[] dist2 = dijkstra(src2, graph, n);
        long[] distToDest = dijkstra(dest, reverseGraph, n);

        long ans = INF;
        for (int i = 0; i < n; i++) {
            if (dist1[i] == INF || dist2[i] == INF || distToDest[i] == INF) continue;
            ans = Math.min(ans, dist1[i] + dist2[i] + distToDest[i]);
        }

        return ans == INF ? -1 : ans;
    }

    private long[] dijkstra(int start, List<int[]>[] graph, int n) {
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, start});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];

            if (d > dist[u]) continue;

            for (int[] e : graph[u]) {
                int v = e[0];
                long w = e[1];
                if (dist[v] > d + w) {
                    dist[v] = d + w;
                    pq.offer(new long[]{dist[v], v});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {

        MinimumWeightedSubGraphWithRequiredPaths obj = new MinimumWeightedSubGraphWithRequiredPaths();
        int n = 6;
        int[][] edges = {{0, 2, 2}, {0, 5, 6}, {1, 0, 3}, {1, 4, 5}, {2, 1, 1}, {2, 3, 3}, {2, 3, 4}, {3, 4, 2}, {4, 5, 1}};
        int src1 = 0;
        int src2 = 1;
        int dest = 5;
        System.out.println(obj.minimumWeight(n, edges, src1, src2, dest));
    }
}


