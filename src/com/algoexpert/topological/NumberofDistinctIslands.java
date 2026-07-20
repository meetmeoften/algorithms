package com.algoexpert.topological;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberofDistinctIslands {

    int[][] dirs = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    public int numDistinctIslands(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        Set<String> set = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    List<String> shape = new ArrayList<>();
                    dfs(grid, visited, i, j, i, j, shape);
                    set.add(String.join(",", shape));
                }
            }
        }

        return set.size();
    }

    private void dfs(int[][] grid, boolean[][] visited,
                     int r, int c,
                     int baseRow, int baseCol,
                     List<String> shape) {

        int m = grid.length;
        int n = grid[0].length;

        visited[r][c] = true;

        // Store relative position
        shape.add((r - baseRow) + ":" + (c - baseCol));

        for (int[] dir : dirs) {

            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr >= 0 && nr < m &&
                    nc >= 0 && nc < n &&
                    grid[nr][nc] == 1 &&
                    !visited[nr][nc]) {

                dfs(grid, visited, nr, nc, baseRow, baseCol, shape);
            }
        }
    }


    public static void main(String[] args) {

        NumberofDistinctIslands sol = new NumberofDistinctIslands();

        int[][] grid = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        };

        int result = sol.numDistinctIslands(grid);

        System.out.println(result);
    }
}
