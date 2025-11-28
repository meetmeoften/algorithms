package com.algoexpert.graph.advanced2;

import java.util.HashSet;
import java.util.Set;

class MakingALargeIslandKFlipsNoDSU1 {
    private int gridSize, currentIslandId;
    private int[] islandSizes;
    private int[][] islandIds;
    private int[][] grid;
    private final int[] directions = {-1, 0, 1, 0, -1};

    public int largestIsland(int[][] grid) {
        gridSize = grid.length;
        islandIds = new int[gridSize][gridSize];
        this.grid = grid;
        islandSizes = new int[gridSize * gridSize + 1];
        int maxIslandSize = 0;

        // First pass: label islands and record their sizes
        for (int row = 0; row < gridSize; ++row) {
            for (int col = 0; col < gridSize; ++col) {
                if (grid[row][col] == 1 && islandIds[row][col] == 0) {
                    ++currentIslandId;
                    maxIslandSize = Math.max(maxIslandSize, dfs(row, col));
                }
            }
        }

        // Second pass: try flipping each 0 to 1
        for (int row = 0; row < gridSize; ++row) {
            for (int col = 0; col < gridSize; ++col) {
                if (grid[row][col] == 0) {
                    Set<Integer> adjacentIslands = new HashSet<>();
                    for (int dir = 0; dir < 4; ++dir) {
                        int newRow = row + directions[dir];
                        int newCol = col + directions[dir + 1];
                        if (newRow >= 0 && newRow < gridSize && newCol >= 0 && newCol < gridSize) {
                            adjacentIslands.add(islandIds[newRow][newCol]);
                        }
                    }
                    int potentialSize = 1; // for the flipped cell
                    for (int islandId : adjacentIslands) {
                        potentialSize += islandSizes[islandId];
                    }
                    maxIslandSize = Math.max(maxIslandSize, potentialSize);
                }
            }
        }
        return maxIslandSize == 0 ? gridSize * gridSize : maxIslandSize;
    }

    private int dfs(int row, int col) {
        islandIds[row][col] = currentIslandId;
        ++islandSizes[currentIslandId];
        for (int dir = 0; dir < 4; ++dir) {
            int newRow = row + directions[dir];
            int newCol = col + directions[dir + 1];
            if (newRow >= 0 && newRow < gridSize &&
                    newCol >= 0 && newCol < gridSize &&
                    grid[newRow][newCol] == 1 &&
                    islandIds[newRow][newCol] == 0) {
                dfs(newRow, newCol);
            }
        }
        return islandSizes[currentIslandId];
    }

    // Driver test
    public static void main(String[] args) {
        MakingALargeIslandKFlipsNoDSU1 obj = new MakingALargeIslandKFlipsNoDSU1();
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
//        System.out.println("Largest island with " + k + " flips: "
//                + obj.largestIsland(grid, k));
    }
}

