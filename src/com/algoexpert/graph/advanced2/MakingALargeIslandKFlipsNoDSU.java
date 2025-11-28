package com.algoexpert.graph.advanced2;

import java.util.*;

public class MakingALargeIslandKFlipsNoDSU {

    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int n;

    public int largestIslandKFlips(int[][] grid, int k) {
        n = grid.length;
        int id = 2;
        Map<Integer, Integer> areaMap = new HashMap<>();
        int maxArea = 0;

        // Step 1️⃣ Label each island and record area
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

        // Step 2️⃣ Multi-source BFS expansion (simulate k flips)
        int[][] dist = new int[n][n];
        int[][] owner = new int[n][n];
        Queue<int[]> q = new LinkedList<>();

        for (int[] row : dist) Arrays.fill(row, -1);

        // initialize queue with all land cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 1) {
                    q.offer(new int[]{i, j});
                    dist[i][j] = 0;
                    owner[i][j] = grid[i][j];
                }
            }
        }

        // BFS expansion through up to k zeros
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            int idOwner = owner[x][y];

            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                if (grid[nx][ny] == 0 && (dist[nx][ny] == -1 || dist[nx][ny] > dist[x][y] + 1)) {
                    int nd = dist[x][y] + 1;
                    if (nd <= k) {
                        grid[nx][ny] = idOwner;      // mark expansion
                        owner[nx][ny] = idOwner;
                        dist[nx][ny] = nd;
                        q.offer(new int[]{nx, ny});
                        areaMap.put(idOwner, areaMap.get(idOwner) + 1); // grew by one
                        maxArea = Math.max(maxArea, areaMap.get(idOwner));
                    }
                }
                // merge overlap: two expanding islands meet
                else if (grid[nx][ny] > 1 && grid[nx][ny] != idOwner) {
                    int newArea = areaMap.get(idOwner) + areaMap.get(grid[nx][ny]);
                    maxArea = Math.max(maxArea, newArea);
                }
            }
        }

        return Math.min(maxArea, n * n);
    }

    // DFS to label islands
    private int dfsLabel(int[][] grid, int i, int j, int id) {
        if (i < 0 || j < 0 || i >= n || j >= n || grid[i][j] != 1) return 0;
        grid[i][j] = id;
        int area = 1;
        for (int[] d : dirs) {
            area += dfsLabel(grid, i + d[0], j + d[1], id);
        }
        return area;
    }

    // Driver test
    public static void main(String[] args) {
        MakingALargeIslandKFlipsNoDSU obj = new MakingALargeIslandKFlipsNoDSU();
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
        System.out.println("Largest island with " + k + " flips: "
                + obj.largestIslandKFlips(grid, k));
    }
}

