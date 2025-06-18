package com.techiedelight.dp.medium.matrix;

import java.util.*;

public class MatrixPathWithGivenCost {

    public static int countPaths(int[][] matrix, int cost) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        Map<String, Integer> memo = new HashMap<>();
        return count(matrix, rows - 1, cols - 1, cost, memo);
    }

    private static int count(int[][] matrix, int x, int y, int cost, Map<String, Integer> memo) {
        // Out of bounds or invalid cost
        if (cost < 0) return 0;

        // Base case: at (0, 0)
        if (x == 0 && y == 0)
            return (matrix[0][0] == cost) ? 1 : 0;

        // Memoization key
        String key = x + "," + y + "," + cost;

        if (memo.containsKey(key)) return memo.get(key);

        int ways = 0;

        // Move up
        if (x > 0)
            ways += count(matrix, x - 1, y, cost - matrix[x][y], memo);

        // Move left
        if (y > 0)
            ways += count(matrix, x, y - 1, cost - matrix[x][y], memo);

        memo.put(key, ways);
        return ways;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 6, 5},
                {3, 2, 1}
        };

        int cost = 12;
        System.out.println("Number of paths with cost " + cost + ": " + countPaths(matrix, cost));
    }
}


