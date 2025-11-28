package com.algoexpert.graph.advanced2;

import com.zjava8.java.C;

import java.util.Arrays;

public class PrimsSimpleExample {

    static int V = 4; // number of vertices

    // Function to find the vertex with minimum key value not yet included in MST
    static int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Prim’s algorithm
    static void primMST(int[][] graph) {
        int[] parent = new int[V];  // to store MST edges
        int[] key = new int[V];     // minimum edge weights
        boolean[] mstSet = new boolean[V]; // included vertices

        // Initialize all keys as infinity
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;    // Start from vertex 0 (A)
        parent[0] = -1; // First node is root of MST

        for (int count = 0; count < V - 1; count++) {
            // Pick vertex not in MST with minimum key
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            // Update key values of adjacent vertices
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Print the constructed MST
        printMST(parent, graph);
    }




    // Print MST edges and total weight
    static void printMST(int[] parent, int[][] graph) {
        int total = 0;
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (int i = 1; i < V; i++) {
            System.out.println((char) ('A' + parent[i]) + " - " + (char) ('A' + i) + " : " + graph[i][parent[i]]);
            total += graph[i][parent[i]];
        }
        System.out.println("Total weight of MST = " + total);
    }

    public static void main(String[] args) {
        // Adjacency matrix of the graph
        int[][] graph = {
                {0, 1, 3, 0},
                {1, 0, 2, 4},
                {3, 2, 0, 5},
                {0, 4, 5, 0}
        };

        primMST(graph);

//             A   B   C   D
//        A [  0,  1,  3,  0 ]
//        B [  1,  0,  2,  4 ]
//        C [  3,  2,  0,  5 ]
//        D [  0,  4,  5,  0 ]

    }
}
