package com.algoexpert.graph.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathWithObstacles {

    public int shortestPath(int[][] grid, int k) {

        int m = grid.length, n = grid[0].length;
        boolean[][][] visited = new boolean[m][n][k + 1];

        Queue<int[]> Q = new LinkedList();
        Q.add(new int[]{0, 0, k});
        //0 index -> x coordinate
        //1 index -> y coordinate
        //2 index -> obstacle count

        int res = 0;

        while (Q.size() > 0) {
            int size = Q.size();

            while (size-- > 0) {

                int[] rem = Q.remove();
                int x = rem[0];
                int y = rem[1];
                int obs = rem[2];

                if (x == m - 1 && y == n - 1 && obs >= 0) return res;     // reached end

                if (obs < 0 || visited[x][y][obs] == true) continue;
                visited[x][y][obs] = true;

                // up
                if (x - 1 >= 0) {
                    Q.add(new int[]{x - 1, y, obs - grid[x - 1][y]});
                }

                // down
                if (x + 1 < m) {
                    Q.add(new int[]{x + 1, y, obs - grid[x + 1][y]});
                }

                // left
                if (y - 1 >= 0) {
                    Q.add(new int[]{x, y - 1, obs - grid[x][y - 1]});
                }

                // right
                if (y + 1 < n) {
                    Q.add(new int[]{x, y + 1, obs - grid[x][y + 1]});
                }
            }
            ++res;
        }
        return -1;
    }

    public int shortestPath2(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;

        // If we can eliminate enough obstacles to always take the shortest Manhattan path
        if (k >= m + n - 2)
            return m + n - 2;

        int[][] dirs = {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };

        int[][] visited = new int[m][n];
        for (int[] row : visited)
            Arrays.fill(row, -1);

        Queue<State> q = new ArrayDeque<>();
        q.offer(new State(0, 0, 0, k));
        visited[0][0] = k;

        while (!q.isEmpty()) {

            State cur = q.poll();

            if (cur.row == m - 1 && cur.col == n - 1)
                return cur.steps;

            for (int[] dir : dirs) {

                int nr = cur.row + dir[0];
                int nc = cur.col + dir[1];

                if (nr < 0 || nc < 0 || nr >= m || nc >= n)
                    continue;

                int remainingK = cur.kLeft - grid[nr][nc];

                if (remainingK < 0)
                    continue;

                // Skip if we've already visited this cell with equal or more remaining eliminations
                if (visited[nr][nc] >= remainingK)
                    continue;

                visited[nr][nc] = remainingK;
                q.offer(new State(nr, nc, cur.steps + 1, remainingK));
            }
        }

        return -1;
    }

    public class State {
        int row;
        int col;
        int steps;
        int kLeft;

        State(int row, int col, int steps, int kLeft) {
            this.row = row;
            this.col = col;
            this.steps = steps;
            this.kLeft = kLeft;
        }
    }


    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0}, {1, 1, 0}, {0, 0, 0}, {0, 1, 1}, {0, 0, 0}};
        ShortestPathWithObstacles matrix = new ShortestPathWithObstacles();
        matrix.shortestPath(grid, 1);
    }
}

