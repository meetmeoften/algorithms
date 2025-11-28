package com.algoexpert.graph.advanced2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsMinHeapExample {

    static class Edge {
        int dest, weight;

        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex, key;

        Node(int vertex, int key) {
            this.vertex = vertex;
            this.key = key;
        }

        public int compareTo(Node other) {
            return this.key - other.key; // min-heap by key
        }
    }

    static int V = 4; // Number of vertices

    // Prim’s algorithm using Min-Heap
    static void primMST(List<List<Edge>> graph) {
        boolean[] visited = new boolean[V]; // MST inclusion flag
        int[] key = new int[V];           // Minimum edge weight to each vertex
        int[] parent = new int[V];        // To store MST structure

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;   // Start from vertex 0 (A)
        parent[0] = -1;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0)); // (vertex, key)

        while (!pq.isEmpty()) {
            int u = pq.poll().vertex; // Get vertex with smallest key
            visited[u] = true;

            // Traverse all adjacent vertices
            for (Edge edge : graph.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;

                // If v is not yet in MST and edge weight is smaller than current key
                if (!visited[v] && weight < key[v]) {
                    key[v] = weight;
                    parent[v] = u;
                    pq.add(new Node(v, key[v]));
                }
            }
        }

        printMST(parent, key);
    }

    // Print MST
    static void printMST(int[] parent, int[] key) {
        int total = 0;
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (int i = 1; i < V; i++) {
            System.out.println((char) ('A' + parent[i]) + " - " + (char) ('A' + i) + " : " + key[i]);
            total += key[i];
        }
        System.out.println("Total weight of MST = " + total);
    }

    public static void main(String[] args) {
        // Create adjacency list for graph
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());

        // Add edges (undirected)
        graph.get(0).add(new Edge(1, 1)); // A-B
        graph.get(0).add(new Edge(2, 3)); // A-C
        graph.get(1).add(new Edge(0, 1)); // B-A
        graph.get(1).add(new Edge(2, 2)); // B-C
        graph.get(1).add(new Edge(3, 4)); // B-D
        graph.get(2).add(new Edge(0, 3)); // C-A
        graph.get(2).add(new Edge(1, 2)); // C-B
        graph.get(2).add(new Edge(3, 5)); // C-D
        graph.get(3).add(new Edge(1, 4)); // D-B
        graph.get(3).add(new Edge(2, 5)); // D-C

        primMST(graph);
    }
}