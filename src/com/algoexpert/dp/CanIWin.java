package com.algoexpert.dp;

public class CanIWin {
    Boolean[] memo;
    int max;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = maxChoosableInteger * (maxChoosableInteger + 1) / 2;
        if (desiredTotal <= 0) return true;
        if (sum < desiredTotal) return false;

        this.max = maxChoosableInteger;
        memo = new Boolean[1 << maxChoosableInteger];

        return dfs(0, desiredTotal);
    }

    private boolean dfs(int usedMask, int remaining) {
        if (remaining <= 0) return false;
        if (memo[usedMask] != null) return memo[usedMask];

        for (int i = 0; i < max; i++) {
            if ((usedMask & (1 << i)) == 0) {
                int pick = i + 1;
                if (pick >= remaining ||
                        !dfs(usedMask | (1 << i), remaining - pick)) {
                    memo[usedMask] = true;
                    return true;
                }
            }
        }
        memo[usedMask] = false;
        return false;
    }

    public static void main(String[] args) {
        CanIWin game = new CanIWin();
        game.canIWin(10, 11);
    }

}
