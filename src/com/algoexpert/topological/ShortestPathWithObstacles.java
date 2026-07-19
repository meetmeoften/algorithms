package com.algoexpert.topological;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathWithObstacles {

    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        if (m == 1 && n == 1)
            return 0;
        if (k >= m + n - 2)
            return m + n - 2;
        boolean[][][] visited = new boolean[m][n][k + 1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, k});
        visited[0][0][k] = true;
        int steps = 0;
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i= 0; i < size; i++) {
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];
                int remain = curr[2];
                if(r ==  m -1 && c == n-1) {
                    return steps;
                }
                for(int[] d : dir) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr < 0 || nc < 0 || nr >= m || nc >= n)
                        continue;
                    int newRemain = remain - grid[nr][nc];
                    if (newRemain >= 0 && !visited[nr][nc][newRemain]) {
                        visited[nr][nc][newRemain] = true;
                        q.offer(new int[]{nr, nc, newRemain});
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
