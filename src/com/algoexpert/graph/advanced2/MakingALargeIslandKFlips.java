package com.algoexpert.graph.advanced2;

import java.util.*;

public class MakingALargeIslandKFlips {

    static class DSU {
        int[] parent, size;

        DSU(int n) {
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

    static int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public int largestIslandKFlips(int[][] grid, int k) {
        int n = grid.length;
        DSU dsu = new DSU(n * n);
        Map<Integer, Integer> areaMap = new HashMap<>();
        int id = 2;
        int maxArea = 0;

        // Step 1️⃣: Label each island with DFS
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = dfsLabel(grid, i, j, id);
                    areaMap.put(id, area);
                    maxArea = Math.max(maxArea, area);
                    id++;
                }
            }
        }

        // Step 2️⃣: Multi-source BFS
        Queue<int[]> q = new LinkedList<>();
        int[][] dist = new int[n][n];
        int[][] owner = new int[n][n]; // which island owns this cell

        for (int[] row : dist) Arrays.fill(row, -1);

        // Initialize BFS with all land cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 1) {
                    q.offer(new int[]{i, j});
                    dist[i][j] = 0;
                    owner[i][j] = grid[i][j];
                }
            }
        }

        // Step 3️⃣: BFS expansion (simulate flips)
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int i = cur[0], j = cur[1];
            int curOwner = owner[i][j];

            for (int[] d : dirs) {
                int ni = i + d[0], nj = j + d[1];
                if (ni < 0 || nj < 0 || ni >= n || nj >= n) continue;

                // Expand into water (0)
                if (grid[ni][nj] == 0 && (dist[ni][nj] == -1 || dist[ni][nj] > dist[i][j] + 1)) {
                    dist[ni][nj] = dist[i][j] + 1;
                    if (dist[ni][nj] <= k) {
                        grid[ni][nj] = curOwner; // mark as reached
                        owner[ni][nj] = curOwner;
                        q.offer(new int[]{ni, nj});
                    }
                }
                // Merge two expanding islands
                else if (grid[ni][nj] > 1 && grid[ni][nj] != curOwner) {
                    dsu.union((curOwner - 2), (grid[ni][nj] - 2));
                    int mergedSize = dsu.getSize(curOwner - 2);
                    maxArea = Math.max(maxArea, mergedSize);
                }
            }
        }

        return Math.min(maxArea, n * n); // cannot exceed total grid
    }

    // Label each island recursively
    private int dfsLabel(int[][] grid, int i, int j, int id) {
        int n = grid.length;
        if (i < 0 || j < 0 || i >= n || j >= n || grid[i][j] != 1) return 0;
        grid[i][j] = id;
        int area = 1;
        for (int[] d : dirs) {
            area += dfsLabel(grid, i + d[0], j + d[1], id);
        }
        return area;
    }

    // Test driver
    public static void main(String[] args) {
        MakingALargeIslandKFlips obj = new MakingALargeIslandKFlips();
//        int[][] grid = {
//                {1, 0, 0, 1},
//                {0, 0, 0, 0},
//                {0, 1, 0, 0},
//                {1, 0, 1, 0}
//        };

        int[][] grid = {
                {1, 0},
                {0, 1}
        };
        int k = 2;
        System.out.println("Largest island with " + k + " flips: " + obj.largestIslandKFlips(grid, k));
    }
}

