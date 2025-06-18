package com.techiedelight.dp.medium.matrix;

public class IslandWalkProbability {

    static int N; // Matrix size
    static double[][][] dp; // Memoization table
    static int[] dx = {0, 0, 1, -1}; // Right, Left, Down, Up
    static int[] dy = {1, -1, 0, 0};

    public static double findProbability(int n, int x, int y, int steps) {
        N = n;
        dp = new double[steps + 1][n][n];

        for (int s = 0; s <= steps; s++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    dp[s][i][j] = -1;


        return helper(x, y, steps);
    }

    private static double helper(int x, int y, int steps) {
        if (x < 0 || y < 0 || x >= N || y >= N)
            return 0;

        if (steps == 0)
            return 1;

        // If already computed
        if (dp[steps][x][y] != -1)
            return dp[steps][x][y];

        double prob = 0;

        // Try all 4 directions
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            prob += 0.25 * helper(nx, ny, steps - 1);
        }
        dp[steps][x][y] = prob;
        return prob;
    }

    public static void main(String[] args) {
        int N = 3;          // 5x5 island
        int x = 1, y = 1;   // Starting in the middle
        int steps = 1;

        double prob = findProbability(N, x, y, steps);
        System.out.printf("Probability of being alive after %d steps: %.6f\n", steps, prob);
    }
}
