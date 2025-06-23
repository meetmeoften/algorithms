package com.algoexpert.graph.advanced;

import java.util.*;

public class MinCostToConnectAllPoints {

	// Prims Algorithm
    public static int minCostConnectPoints(int[][] points) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // edge weight, the index of next node
        pq.offer(new int[]{0, 0});
        int len = points.length;
        Set<Integer> visited = new HashSet<>();
        int cost = 0;

        // When visited.size() == points.len meaning that all the nodes has been connected.
        while (visited.size() < len) {
            int[] arr = pq.poll();

            int weight = arr[0];
            int currNode = arr[1];

            if (visited.contains(currNode)) {
                continue;
            }

            visited.add(currNode);
            cost += weight;

            for (int nextNode = 0; nextNode < len; nextNode++) {
                if (!visited.contains(nextNode)) {
                    int nextWeight = Math.abs(points[nextNode][0] - points[currNode][0]) + Math.abs(points[nextNode][1] - points[currNode][1]);
                    System.out.println(nextWeight + "  " + nextNode);
                    pq.add(new int[]{nextWeight, nextNode});
                }
            }
        }

        return cost;
    }

//Kruskals Algorithm
    public int minCostConnectPoints2(int[][] points) {
        int n = points.length;
        List<int[]> edges = new ArrayList<>();

        // Step 1: Create all possible edges with their weights (Manhattan distance)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int weight = Math.abs(points[i][0] - points[j][0]) +
                        Math.abs(points[i][1] - points[j][1]);
                edges.add(new int[]{i, j, weight});
            }
        }

        // Step 2: Sort edges by weight
        edges.sort(Comparator.comparingInt(a -> a[2]));

        // Step 3: Initialize Union-Find
        UnionFind uf = new UnionFind(n);
        int cost = 0, count = 0;

        // Step 4: Add edges to MST
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if (uf.union(u, v)) {
                cost += w;
                count++;
                if (count == n - 1) break; // MST is complete
            }
        }

        return cost;
    }

    // Union-Find with path compression
    class UnionFind {
        int[] parent;

        UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]); // Path compression
            return parent[x];
        }

        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return false;
            parent[rootX] = rootY;
            return true;
        }
    }


    public static void main(String[] args) {
        // int[][] points = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        int[][] points = {{3, 12}, {-2, 5}, {-4, 1}};
        minCostConnectPoints(points);
    }

}
