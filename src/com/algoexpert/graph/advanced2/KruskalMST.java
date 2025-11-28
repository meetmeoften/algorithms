package com.algoexpert.graph.advanced2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalMST {

    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight; // sort by weight
        }
    }

    public static int kruskalMST(int V, List<Edge> edges) {
        // Sort edges by weight
        Collections.sort(edges);

        UnionFind uf = new UnionFind(V);
        int mstWeight = 0;
        List<Edge> mstEdges = new ArrayList<>();

        for (Edge edge : edges) {
            int rootU = uf.find(edge.src);
            int rootV = uf.find(edge.dest);

            // If adding this edge doesn't cause a cycle
            if (rootU != rootV) {
                uf.union(rootU, rootV);
                mstWeight += edge.weight;
                mstEdges.add(edge);
            }
        }

        // Print MST edges
        System.out.println("Edges in MST:");
        for (Edge e : mstEdges) {
            System.out.println(e.src + " - " + e.dest + " : " + e.weight);
        }

        return mstWeight;
    }

    public static void main(String[] args) {
        int V = 4; // number of vertices
        List<Edge> edges = new ArrayList<>();

        // Add edges (undirected)
        edges.add(new Edge(0, 1, 1));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(1, 3, 4));
        edges.add(new Edge(2, 3, 5));

        int mstWeight = kruskalMST(V, edges);
        System.out.println("Total weight of MST: " + mstWeight);
    }
}
