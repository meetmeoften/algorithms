package com.algoexpert.dp;

public class StoneGame4 {

    Boolean[] memo;

    public boolean winnerSquareGame(int n) {
        memo = new Boolean[n + 1];
        return dfs(n);
    }

    private boolean dfs(int n) {
        if (n == 0) return false;
        if (memo[n] != null) return memo[n];

        for (int k = 1; k * k <= n; k++) {
            if (!dfs(n - k * k)) {
                memo[n] = true;
                return true;
            }
        }

        memo[n] = false;
        return false;
    }

    public static void main(String[] args) {
        StoneGame4 game = new StoneGame4();
        game.winnerSquareGame(4);
    }
}
