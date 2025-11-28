package com.algoexpert.graph.advanced2;

import java.util.*;

public class MakingALargeIslandDSU {

    class DSU {
        int[] parent, size;
        int n;

        DSU(int n) {
            this.n = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return;
            if (size[rootX] < size[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
        }

        int getSize(int x) {
            return size[find(x)];
        }
    }

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        DSU dsu = new DSU(n * n);

        // Step 1️⃣: Union all adjacent 1s
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int id1 = i * n + j;
                    for (int[] d : dirs) {
                        int ni = i + d[0], nj = j + d[1];
                        if (ni >= 0 && nj >= 0 && ni < n && nj < n && grid[ni][nj] == 1) {
                            int id2 = ni * n + nj;
                            dsu.union(id1, id2);
                        }
                    }
                }
            }
        }

        // Step 2️⃣: Compute the largest island without flipping
        int maxArea = 0;
        for (int i = 0; i < n * n; i++) {
            if (grid[i / n][i % n] == 1) {
                maxArea = Math.max(maxArea, dsu.getSize(i));
            }
        }

        // Step 3️⃣: Try flipping each 0 → 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> seen = new HashSet<>();
                    int area = 1; // the flipped cell
                    for (int[] d : dirs) {
                        int ni = i + d[0], nj = j + d[1];
                        if (ni >= 0 && nj >= 0 && ni < n && nj < n && grid[ni][nj] == 1) {
                            int root = dsu.find(ni * n + nj);
                            if (seen.add(root)) {
                                area += dsu.size[root];
                            }
                        }
                    }
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        MakingALargeIslandDSU obj = new MakingALargeIslandDSU();
        int[][] grid = {
                {1, 1},
                {0, 1}
        };
        System.out.println(obj.largestIsland(grid)); // Output: 3
    }
}

