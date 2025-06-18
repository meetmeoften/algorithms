package com.algoexpert2.graph;

import java.util.*;

public class Dijkstra {

    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        int n = 3;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        graph.get(0).add(new Edge(1, 1));
        graph.get(0).add(new Edge(2, 4));
        graph.get(1).add(new Edge(2, 2));

        dijkstra(graph, 0);

    }

    public static void dijkstra(List<List<Edge>> graph, int source) {
        int n= graph.size();

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.offer(new Edge(source, 0));

        while(!pq.isEmpty()) {
            Edge current = pq.poll();
            int u = current.to;
            for(Edge edge: graph.get(u)) {
                int v = edge.to;
                int w = edge.weight;
                if(dist[u] + w < dist[v] ) {
                    dist[v] = dist[u] + w;
                    pq.offer(new Edge(v, dist[v]));
                }
            }
        }
        // Print the result
        System.out.println("Shortest distances from node " + source + ":");
        for (int i = 0; i < n; i++) {
            System.out.println("To node " + i + ": " + dist[i]);
        }
    }

    public static void bellmanFord(List<Edge> edges, int V, int source) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
    }
}
