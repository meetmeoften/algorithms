package com.techiedelight.dp.medium.stairs;

public class TilingRecursive {


    /**
     * To find the number of ways to tile an N × 4 matrix using 1 × 4 tiles in Java, you're essentially
     * solving a dynamic programming problem.
     *
     * 💡 Problem Understanding:
     * You're trying to fill a matrix of N rows and 4 columns using tiles that are 1 row tall and
     * 4 columns wide. You can place each tile either:
     *
     * Horizontally: takes up one row entirely
     *
     * Vertically: takes 4 rows in a single column (not allowed here since tile is only 1×4)
     *
     * But since you're only using 1×4 tiles, they can only be placed horizontally and only if there is a full row to fit them.
     *
     * 🧠 Dynamic Programming Insight
     * Let dp[n] be the number of ways to fill an n×4 matrix.
     *
     * Base cases:
     *
     * dp[0] = 1 (an empty matrix has one way to be filled – do nothing)
     *
     * dp[1] = 1 (you can't place any tiles – so only one way: leave it empty)
     *
     * dp[2] = 1 (same as above)
     *
     * dp[3] = 1
     *
     * dp[4] = 2 (either place 4 tiles horizontally in each row, or one tile vertically across all 4 rows in a column)
     *
     * Recurrence:
     *
     * For n >= 4: dp[n] = dp[n-1] + dp[n-4]
     *
     * dp[n-1]: means last row is not tiled
     *
     * dp[n-4]: means last 4 rows are filled by 1×4 tiles horizontally
     * @param n
     * @return
     */
    public static int countWays(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        return countWays(n - 1) + countWays(n - 4);
    }

    public static void main(String[] args) {
        int N = 8;
        System.out.println("Number of ways to fill " + N + "x4 matrix: " + countWays(N));
    }
}
