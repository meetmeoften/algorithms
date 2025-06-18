package com.techiedelight.dp.medium.matrix;

public class MaxSnakeSequence {
    // Possible directions: right and down
    private static final int[] dx = {0, 1};
    private static final int[] dy = {1, 0};

    private static int n; // Size of square matrix
    private static int[][] memo;

    public static void main(String[] args) {
        // Example matrix
        int[][] matrix = {
                {9, 6, 5, 2},
                {8, 7, 6, 5},
                {7, 3, 1, 6},
                {1, 1, 1, 7}
        };

        findMaxSnakeSequence(matrix);
    }

    public static void findMaxSnakeSequence(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            System.out.println("Invalid matrix");
            return;
        }

        n = matrix.length;
        memo = new int[n][n]; // Memoization table

        int maxLength = 0;
        int startX = 0, startY = 0;

        // Try starting the snake from each cell
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int length = dfs(matrix, i, j, -1, -1);
                if (length > maxLength) {
                    maxLength = length;
                    startX = i;
                    startY = j;
                }
            }
        }

        System.out.println("Maximum snake length: " + maxLength);

        // Print the sequence
        System.out.println("Snake sequence:");
        printSequence(matrix, startX, startY);
    }

    private static int dfs(int[][] matrix, int x, int y, int prevX, int prevY) {
        // If we have already computed this state or it's invalid, return 0
        if (x < 0 || x >=   n || y < 0 || y >= n) {
            return 0;
        }

        // Check if this is a valid next cell in the sequence
        if (prevX != -1 && prevY != -1) {
            int diff = Math.abs(matrix[x][y] - matrix[prevX][prevY]);
            if (diff != 1) {
                return 0;
            }
        }

        // If already computed, return the result
        if (memo[x][y] != 0 && prevX != -1) {
            return memo[x][y];
        }

        int maxLength = 1; // At minimum, a single cell is a valid sequence

        // Try right and down directions
        for (int d = 0; d < 2; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // Check if the next position is valid
            if (nx < n && ny < n) {
                int diff = Math.abs(matrix[nx][ny] - matrix[x][y]);
                if (diff == 1) {
                    maxLength = Math.max(maxLength, 1 + dfs(matrix, nx, ny, x, y));
                }
            }
        }

        // Store the result in the memoization table
        memo[x][y] = maxLength;
        return maxLength;
    }

    private static void printSequence(int[][] matrix, int startX, int startY) {
        System.out.print(matrix[startX][startY] + " ");

        int x = startX, y = startY;
        int currentValue = matrix[x][y];

        // Continue until we can't extend the sequence anymore
        while (true) {
            boolean found = false;

            // Try right and down directions
            for (int d = 0; d < 2; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                // Check if the next position is valid and the value differs by 1
                if (nx < n && ny < n && Math.abs(matrix[nx][ny] - currentValue) == 1) {
                    System.out.print(matrix[nx][ny] + " ");
                    x = nx;
                    y = ny;
                    currentValue = matrix[x][y];
                    found = true;
                    break;
                }
            }

            if (!found) break;
        }
        System.out.println();
    }
}
