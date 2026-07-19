package com.algoexpert.topological;

public class RatInMaze2 {

    private static int N;

    // Direction vectors: Right, Down, Left, Up
    private static final int[] rowDir = {0, 1, 0, -1};
    private static final int[] colDir = {1, 0, -1, 0};
    private static final char[] directions = {'R', 'D', 'L', 'U'};

    public static int[][] solveMaze(int[][] maze) {
        N = maze.length;
        int[][] solution = new int[N][N];

        if (solveMazeUtil(maze, 0, 0, solution)) {
            return solution;
        }
        return null;
    }

    private static boolean solveMazeUtil(int[][] maze, int x, int y, int[][] solution) {
        if (x == N - 1 && y == N - 1) {
            solution[x][y] = 1;
            return true;
        }

        if (isValid(maze, x, y)) {
            solution[x][y] = 1;

            // Try all 4 directions
            for (int dir = 0; dir < 4; dir++) {
                int newX = x + rowDir[dir];
                int newY = y + colDir[dir];

                if (solveMazeUtil(maze, newX, newY, solution)) {
                    return true;
                }
            }

            // Backtrack
            solution[x][y] = 0;
            return false;
        }

        return false;
    }

    private static boolean isValid(int[][] maze, int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1);
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

        int[][] solution = solveMaze(maze1);

        if (solution != null) {
            System.out.println("Path found:");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(solution[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No path exists!");
        }
    }
}

