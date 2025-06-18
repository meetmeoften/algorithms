package com.algoexpert2.graph;

import java.util.*;

public class BellmanFord {

    static class Edge {
        int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void bellmanFord(List<Edge> edges, int V, int source) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Relax all edges (V - 1) times
        for (int i = 1; i < V; i++) {
            for (Edge edge : edges) {
                if (dist[edge.from] != Integer.MAX_VALUE &&
                        dist[edge.from] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.weight;
                }
            }
        }

        // Check for negative weight cycles
        for (Edge edge : edges) {
            if (dist[edge.from] != Integer.MAX_VALUE &&
                    dist[edge.from] + edge.weight < dist[edge.to]) {
                System.out.println("Graph contains a negative weight cycle!");
                return;
            }
        }

        // Print distances
        System.out.println("Shortest distances from node " + source + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("To node " + i + ": " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }

    public static void main(String[] args) {
        int V = 5; // Number of vertices
        List<Edge> edges = new ArrayList<>();

        // Add edges: edges.add(new Edge(from, to, weight));
        edges.add(new Edge(0, 1, -1));
        edges.add(new Edge(0, 2, 4));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(1, 4, 2));
        edges.add(new Edge(3, 2, 5));
        edges.add(new Edge(3, 1, 1));
        edges.add(new Edge(4, 3, -3));

        bellmanFord(edges, V, 0); // source node = 0
    }
}

