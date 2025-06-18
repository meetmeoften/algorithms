package com.techiedelight.dp.medium.matrix;

public class LongestConsecutivePath {

    static int[] dx = {-1, 0, 1, 0}; // Up, Right, Down, Left
    static int[] dy = {0, 1, 0, -1};
    static int rows, cols;

    public static int longestPath(char[][] matrix, char startChar) {
        rows = matrix.length;
        cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int maxLen = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == startChar) {
                    int len = dfs(matrix, i, j, dp);
                    maxLen = Math.max(maxLen, len);
                }
            }
        }
        return maxLen;
    }

    private static int dfs(char[][] matrix, int x, int y, int[][] dp) {
        if (dp[x][y] != 0)
            return dp[x][y];

        int maxLen = 1;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isValid(nx, ny) && matrix[nx][ny] == matrix[x][y] + 1) {
                maxLen = Math.max(maxLen, 1 + dfs(matrix, nx, ny, dp));
            }
        }
        dp[x][y] = maxLen;
        return maxLen;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < rows && y < cols;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'D', 'E', 'H'},
                {'C', 'F', 'I'},
                {'B', 'G', 'J'}
        };
        char start = 'C';
        int length = longestPath(matrix, start);
        System.out.println("Length of longest consecutive path starting from '" + start + "': " + length);
    }
}
