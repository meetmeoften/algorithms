package com.algoexpert.graph.leetcode;

public class DetectCycleIn2DGrid {

    public static void main(String[] args) {
        char[][] grid = {
                {'a', 'a', 'a'},
                {'a', 'b', 'b'},
                {'a', 'b', 'b'}
        };

        Solution solution = new Solution();
        boolean hasCycle = solution.containsCycle(grid);
        System.out.println("Does the grid contain a cycle? " + hasCycle);
    }

    static class Solution {
        private int rows, cols;
        private final int[] directions = {-1, 0, 1, 0, -1};

        public boolean containsCycle(char[][] grid) {
            rows = grid.length;
            cols = grid[0].length;
            boolean[][] visited = new boolean[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (!visited[i][j]) {
                        if (dfs(grid, visited, -1, -1, i, j, grid[i][j])) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        private boolean dfs(char[][] grid, boolean[][] visited, int parentX, int parentY, int x, int y, char startChar) {
            if (visited[x][y]) {
                return true; // Cycle detected
            }

            visited[x][y] = true;

            for (int k = 0; k < 4; k++) {
                int newX = x + directions[k];
                int newY = y + directions[k + 1];

                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols) {
                    if (grid[newX][newY] == startChar) {
                        // Ignore the cell we came from
                        if (newX == parentX && newY == parentY) {
                            continue;
                        }

                        if (dfs(grid, visited, x, y, newX, newY, startChar)) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }
    }
}
