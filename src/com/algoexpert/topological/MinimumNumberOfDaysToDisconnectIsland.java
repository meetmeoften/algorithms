//package com.algoexpert.topological;
//
//public class MinimumNumberOfDaysToDisconnectIsland {
//
//    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
//
//    public int minDays(int[][] grid) {
//        if (countIslands(grid) != 1) return 0;
//        int m = grid.length;
//        int n = grid[0].length;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if(grid[i][j] == 1) {
//                    grid[i][j] = 0;
//                    if(countIslands(grid) != 1) {
//
//                    }
//                }
//            }
//        }
//        return -1;
//    }
//
//    private void dfs(int[][] grid, int br, int bc, int r, int c) {
//        String db =
//    }
//
//
//    private int countIslands(int[][] grid) {
//        int m = grid.length;
//        int n = grid[0].length;
//        boolean[][] visited = new boolean[m][n];
//        int count = 0;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j] == 1 && !visited[i][j]) {
//                    dfs(grid, i, j);
//                    count++;
//                    if (count > 1) {
//                        return count;
//                    }
//                }
//            }
//        }
//        return count;
//    }
//
//    private void dfs(int[][] grid, boolean[][] visited, int i, int j) {
//        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1 || visited[i][j]) {
//            return;
//        }
//        visited[i][j] = true;
//        for (int[] dir : dirs) {
//            dfs(grid, visited, i + dir[0], j + dir[1]);
//        }
//    }
//
//}
