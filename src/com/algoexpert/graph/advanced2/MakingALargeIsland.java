package com.algoexpert.graph.advanced2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MakingALargeIsland {
    int n;
    int[][] grid;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int largestIsland(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        Map<Integer, Integer> areaMap = new HashMap<>();
        int id = 2; // start island ids from 2 (since grid has 0 and 1)
        int maxArea = 0;

        // 1️⃣ Label each island with a unique id and store area
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(i, j, id);
                    areaMap.put(id, area);
                    maxArea = Math.max(maxArea, area); // track largest existing island
                    id++;
                }
            }
        }

        // 2️⃣ Try flipping each 0 → 1 and calculate new island size
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> neighbors = new HashSet<>();
                    for (int[] d : dirs) {
                        int ni = i + d[0], nj = j + d[1];
                        if (ni >= 0 && nj >= 0 && ni < n && nj < n && grid[ni][nj] > 1) {
                            neighbors.add(grid[ni][nj]);
                        }
                    }

                    int newArea = 1; // flipped cell
                    for (int nid : neighbors) newArea += areaMap.get(nid);
                    maxArea = Math.max(maxArea, newArea);
                }
            }
        }

        return maxArea;
    }

    // DFS to label island and count area
    private int dfs(int i, int j, int id) {
        if (i < 0 || j < 0 || i >= n || j >= n || grid[i][j] != 1) return 0;
        grid[i][j] = id;
        int area = 1;
        for (int[] d : dirs) {
            area += dfs(i + d[0], j + d[1], id);
        }
        return area;
    }

    public static void main(String[] args) {
        MakingALargeIsland obj = new MakingALargeIsland();
        int[][] grid = {
                {1, 0, 1},
                {1, 0, 1},
                {1, 0, 1}
        };
        int[][] grid2 = {
                {1, 0},
                {0, 1}
        };
        System.out.println(obj.largestIsland(grid2)); // Output: 3
    }
}

