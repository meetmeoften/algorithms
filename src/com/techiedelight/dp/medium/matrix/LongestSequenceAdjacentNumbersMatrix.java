package com.techiedelight.dp.medium.matrix;

import java.util.ArrayList;
import java.util.List;

public class LongestSequenceAdjacentNumbersMatrix {

    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int N;

    public static List<Integer> longestPath(int[][] matrix) {
        N = matrix.length;
        List<Integer>[][] dp = new ArrayList[N][N];
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                List<Integer> path = dfs(matrix, i, j, dp);
                if (path.size() > result.size()) {
                    result = path;
                }
            }
        }

        return result;
    }

    private static List<Integer> dfs(int[][] matrix, int x, int y, List<Integer>[][] dp) {
        if (dp[x][y] != null) return dp[x][y];

        List<Integer> maxPath = new ArrayList<>();
        maxPath.add(matrix[x][y]);

        for (int[] dir : directions) {
            int newX = x + dir[0], newY = y + dir[1];

            if (isValid(newX, newY) && matrix[newX][newY] == matrix[x][y] + 1) {
                List<Integer> nextPath = dfs(matrix, newX, newY, dp);
                if (nextPath.size() + 1 > maxPath.size()) {
                    maxPath = new ArrayList<>();
                    maxPath.add(matrix[x][y]);
                    maxPath.addAll(nextPath);
                }
            }
        }
        dp[x][y] = maxPath;
        return dp[x][y];
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    // Example usage
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 9},
                {5, 3, 8},
                {4, 6, 7}
        };

        List<Integer> longestSequence = longestPath(matrix);
        System.out.println("Longest path: " + longestSequence);
    }
}
