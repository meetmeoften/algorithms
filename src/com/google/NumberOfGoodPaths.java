package com.google;

import java.util.*;

public class NumberOfGoodPaths {

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        // Build adjacency list
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        Map<Integer, List<Integer>> valueToNodes = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            valueToNodes.computeIfAbsent(vals[i], k -> new ArrayList<>()).add(i);
        }

        UnionFind uf = new UnionFind(n);

        int goodPaths = 0;
        for (Map.Entry<Integer, List<Integer>> entry : valueToNodes.entrySet()) {
            List<Integer> nodes = entry.getValue();
            for (int node : nodes) {
                for (int neighbor : adjList.get(node)) {
                    if (vals[neighbor] <= vals[node]) {
                        uf.union(node, neighbor);
                    }
                }
            }
            // Count nodes with the same value in each connected component
            Map<Integer, Integer> componentCount = new HashMap<>();
            for (int node : nodes) {
                int root = uf.find(node);
                componentCount.put(root, componentCount.getOrDefault(root, 0) + 1);
            }
            // For each component, calculate number of good paths
            for (int count : componentCount.values()) {
                // Number of ways to choose 2 nodes from count nodes = count * (count + 1) / 2
                goodPaths += count * (count + 1) / 2;
            }
        }
        return goodPaths;
    }

    class UnionFind {

        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return;

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }

    }

    public static void main(String[] args) {
        int vals[] = {1,3,2,1,3};
        int edges[][] = {{0,1},{0,2},{2,3},{2,4}};

        NumberOfGoodPaths numberOfGoodPaths = new NumberOfGoodPaths();
        numberOfGoodPaths.numberOfGoodPaths(vals, edges);
    }
}
