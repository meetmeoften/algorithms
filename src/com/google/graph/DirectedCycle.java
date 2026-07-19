package com.google.graph;

import java.util.*;

public class DirectedCycle {

    private Map<Integer, List<Integer>> graph;
    private boolean[] visited;
    private boolean[] recStack;

    public DirectedCycle(Map<Integer, List<Integer>> graph, int n) {
        this.graph = graph;
        visited = new boolean[n];
        recStack = new boolean[n];
    }

    public boolean hasCycle() {
        for (int node : graph.keySet()) {
            if (!visited[node]) {
                if (dfs(node)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int node) {
        visited[node] = true;
        recStack[node] = true;

        for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (!visited[neighbor] && dfs(neighbor)) {
                return true;
            } else if (recStack[neighbor]) {
                return true;
            }
        }

        recStack[node] = false; // backtrack
        return false;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1));
        graph.put(1, Arrays.asList(2));
     //   graph.put(2, Arrays.asList(0)); // cycle 0->1->2->0

        DirectedCycle dc = new DirectedCycle(graph, 3);
        System.out.println("Directed graph has cycle? " + dc.hasCycle());
    }
}


