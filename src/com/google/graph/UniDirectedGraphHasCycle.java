package com.google.graph;

import java.util.ArrayList;
import java.util.List;

public class UniDirectedGraphHasCycle {

    static class Graph {
        private int V; // Number of vertices
        private List<List<Integer>> adj; // Adjacency list

        public Graph(int V) {
            this.V = V;
            adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        public void addEdge(int u, int v) {
            adj.get(u).add(v);
            adj.get(v).add(u); // Since the graph is undirected
        }

        private boolean dfsUtil(int v, boolean[] visited, int parent) {
            visited[v] = true;
            for (int neighbor : adj.get(v)) {
                if (!visited[neighbor]) {
                    if (dfsUtil(neighbor, visited, v))
                        return true;
                } else if (neighbor != parent) {
                    // Visited and not parent figure out a cycle
                    return true;
                }
            }
            return false;
        }

        public boolean hasCycle() {
            boolean[] visited = new boolean[V];
            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    if (dfsUtil(i, visited, -1))
                        return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 0); // Creates a cycle

        System.out.println("Cycle present: " + g.hasCycle());  // Output: true
    }

}
