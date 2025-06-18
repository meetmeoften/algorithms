package com.techiedelight.dp.hard.partition;

public class PotsOfGold {

    private int[][] memo;

    public int maxCoins(int[] arr) {
        int n = arr.length;
        memo = new int[n][n];
        return solve(arr, 0, n - 1);
    }

    private int solve(int[] arr, int i, int j) {
        if (i > j) return 0;
        if (memo[i][j] != 0) return memo[i][j];
        if (i == j) return arr[i];
        //if (i + 1 == j) return Math.max(arr[i], arr[j]); // Not required mandatorily

        // Player chooses i or j, opponent then chooses optimally
        int pickLeft = arr[i] + Math.min(solve(arr, i + 2, j), solve(arr, i + 1, j - 1));
        int pickRight = arr[j] + Math.min(solve(arr, i + 1, j - 1), solve(arr, i, j - 2));

        memo[i][j] = Math.max(pickLeft, pickRight);
        return memo[i][j];
    }

    // Example usage
    public static void main(String[] args) {
        PotsOfGold solver = new PotsOfGold();
        int[] coins = {4, 6, 2, 3};
        System.out.println("Max coins first player can collect: " + solver.maxCoins(coins)); // Output: 7
    }
}
