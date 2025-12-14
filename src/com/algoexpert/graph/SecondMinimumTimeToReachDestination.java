package com.algoexpert.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class SecondMinimumTimeToReachDestination {

    public int secondMinimum(int n, int[][] edges, int time, int change) {
        // Build graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        // dist[node][0] = shortest
        // dist[node][1] = second shortest
        int[][] dist = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> a[0] - b[0]
        );

        pq.offer(new int[]{0, 1});
        dist[1][0] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int currTime = cur[0];
            int node = cur[1];

            for (int next : graph.get(node)) {
                int t = currTime;

                // wait for green signal if red
                if ((t / change) % 2 == 1) {
                    t += change - (t % change);
                }

                t += time;

                if (t < dist[next][0]) {
                    dist[next][1] = dist[next][0];
                    dist[next][0] = t;
                    pq.offer(new int[]{t, next});
                } else if (t > dist[next][0] && t < dist[next][1]) {
                    dist[next][1] = t;
                    pq.offer(new int[]{t, next});
                }
            }
        }
        return dist[n][1];
    }

    public static void main(String[] args) {
        SecondMinimumTimeToReachDestination s = new SecondMinimumTimeToReachDestination();
        int[][] edges = {{1, 2}, {1, 3}, {1, 4}, {3, 2}, {3, 5}};
        int n = 5;
        int time = 3;
        int change = 5;
        int ans = s.secondMinimum(n, edges, time, change);
        System.out.println(ans);
    }
}
