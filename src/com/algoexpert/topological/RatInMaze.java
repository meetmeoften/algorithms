package com.algoexpert.topological;

import java.util.ArrayList;
import java.util.List;

public class RatInMaze {

    static ArrayList<String> ans;
    static int n;

    public static ArrayList<String> findPath(int[][] maze) {
        ans = new ArrayList<>();
        n = maze.length;
        if (maze[0][0] == 0 || maze[n-1][n-1] == 0) {
            return ans;
        }
        boolean[][] visited = new boolean[n][n];
        dfs(0, 0, maze, visited, "");
        return ans;
    }

    static void dfs(int i, int j, int[][] maze,
                    boolean[][] visited, String path) {
        if (i == n - 1 && j == n - 1) {
            ans.add(path);
            return;
        }
        visited[i][j] = true;
        // Down
        if (isSafe(i + 1, j, maze, visited))
            dfs(i + 1, j, maze, visited, path + "D");
        // Left
        if (isSafe(i, j - 1, maze, visited))
            dfs(i, j - 1, maze, visited, path + "L");
        // Right
        if (isSafe(i, j + 1, maze, visited))
            dfs(i, j + 1, maze, visited, path + "R");
        // Up
        if (isSafe(i - 1, j, maze, visited))
            dfs(i - 1, j, maze, visited, path + "U");
        visited[i][j] = true;

    }

    static boolean isSafe(int i, int j, int[][] maze,
                          boolean[][] visited) {
        return i >= 0 && j >= 0 &&
                i < n && j < n &&
                maze[i][j] == 1 &&
                !visited[i][j];
    }

    public static void main(String[] args) {
        int[][] maze = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };

        int[][] maze1 = {
                {1, 1},
                {1, 1}

        };

        List<String> paths = findPath(maze1);

        if (paths.isEmpty()) {
            System.out.println("No path exists!");
        } else {
            System.out.println("All possible paths:");
            for (String path : paths) {
                System.out.println(path);
            }
            System.out.println("Total paths: " + paths.size());
        }
    }

}
