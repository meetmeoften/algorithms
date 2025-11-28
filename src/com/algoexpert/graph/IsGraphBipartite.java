package com.algoexpert.graph;

import java.util.*;

public class IsGraphBipartite {


    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);  // -1 means uncolored

        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {  // not colored yet
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                color[i] = 0;  // start coloring

                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    for (int neighbor : graph[node]) {
                        if (color[neighbor] == -1) {
                            color[neighbor] = 1 - color[node];  // alternate color
                            queue.offer(neighbor);
                        } else if (color[neighbor] == color[node]) {
                            return false;  // same color on adjacent nodes
                        }
                    }
                }
            }
        }
        return true;  // no conflicts found
    }

    // DFS
    public static boolean isBipartite2(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1); // -1 means uncolored

        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                if (!dfs(graph, color, i, 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean dfs(int[][] graph, int[] color, int node, int c) {
        if (color[node] != -1) {
            return color[node] == c;
        }
        color[node] = c;
        for (int neighbor : graph[node]) {
            if (!dfs(graph, color, neighbor, 1 - c)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Example graph input (adjacency list)
        int[][] graph = {
                {1, 3},
                {0, 2},
                {1, 3},
                {0, 2}
        };

        boolean result = IsGraphBipartite.isBipartite(graph);
        System.out.println("Is the graph bipartite? " + result);
    }
}

