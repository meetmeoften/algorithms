package com.algoexpert2.graph;

import java.util.*;

public class TwoEdgeConnectedGraphTarjan {

    /**
     * You are given an undirected graph represented as an adjacency list.
     * Your task is to determine whether the graph is two-edge-connected.
     *
     * A graph is two-edge-connected if:
     *
     * It is connected (all vertices reachable), and
     *
     * It has no bridges (removing any single edge does NOT disconnect the graph).
     *
     * Return true if the graph meets these criteria; otherwise false.
     *
     * You may assume:
     *
     * The graph can have 0 or more vertices.
     *
     * If the graph has 0 or 1 vertex, consider it two-edge-connected.
     * @param graph
     * @return
     */


    /**
     * Key Idea
     *
     * Use a DFS with Low-Link values (Tarjan’s algorithm) to detect if any edge is a bridge.
     *
     * During DFS:
     *
     * disc[v] = discovery time of vertex v
     *
     * low[v] = earliest discovered vertex reachable from v (via back-edges)
     *
     * For an edge (u, v) to be a bridge, the condition is:
     *
     * low[v] > disc[u]
     *
     *
     * If we discover any bridge → graph is NOT two-edge-connected.
     * @param graph
     * @return
     */

    public static boolean isTwoEdgeConnected(List<List<Integer>> graph) {
        int n = graph.size();
        if (n <= 1) return true;

        int[] disc = new int[n];
        int[] low = new int[n];
        Arrays.fill(disc, -1);

        int[] time = {0};
        boolean[] foundBridge = {false};

        // Run DFS from vertex 0
        dfs(0, -1, graph, disc, low, time, foundBridge);

        // If a bridge was found, not two-edge-connected
        if (foundBridge[0]) return false;

        // Verify the graph is connected (all vertices visited)
        for (int d : disc) {
            if (d == -1) return false;
        }

        return true;
    }

    private static void dfs(int node, int parent, List<List<Integer>> graph,
                            int[] disc, int[] low, int[] time, boolean[] foundBridge) {

        disc[node] = low[node] = time[0]++;

        for (int neighbor : graph.get(node)) {
            if (neighbor == parent) continue;

            if (disc[neighbor] == -1) {
                // Tree edge
                dfs(neighbor, node, graph, disc, low, time, foundBridge);

                low[node] = Math.min(low[node], low[neighbor]);

                // Bridge check
                if (low[neighbor] > disc[node]) {
                    foundBridge[0] = true;
                }

            } else {
                // Back edge
                low[node] = Math.min(low[node], disc[neighbor]);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();

        graph.add(Arrays.asList(1, 2));   // 0
        graph.add(Arrays.asList(0, 2));   // 1
        graph.add(Arrays.asList(0, 1));

//        graph.add(Arrays.asList(1));   // 0
//        graph.add(Arrays.asList(0, 2));   // 1
//        graph.add(Arrays.asList(1));  // 2

        System.out.println(isTwoEdgeConnected(graph)); // true
    }
}

