package com.google;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MinCostToMakeOneValidPath {

    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Deque<int[]> deque = new ArrayDeque<>();
        deque.offerFirst(new int[]{0, 0});
        dist[0][0] = 0;

        // directions: right, left, down, up
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!deque.isEmpty()) {
            int[] cell = deque.pollFirst();
            int x = cell[0], y = cell[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                int val = grid[x][y];
                int cost = dist[x][y] + (val == i + 1 ? 0 : 1);

                if (cost < dist[nx][ny]) {
                    dist[nx][ny] = cost;
                    if (val == i + 1) {
                        deque.offerFirst(new int[]{nx, ny});
                    } else {
                        deque.offerLast(new int[]{nx, ny});
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1}, {2, 2, 2}, {1, 1, 1}};
        System.out.println(new MinCostToMakeOneValidPath().minCost(grid));
    }

}

