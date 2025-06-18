package com.techiedelight.dp.medium.stairs;

public class NthStairWithMSteps {

    public static int countWays(int n, int m) {
        if (n < 0) return 0;  // No way to reach a negative stair
        if (n <= 1) return 1;  // 1 way to reach stair 0 or 1

        int ways = 0;
        for(int i=1; i <=m; i++) {
            ways = ways + countWays(n-i, m);
        }
        return ways;
    }

    public static void main(String[] args) {
        int n = 2; // total stairs
        int m = 1; // max steps per move
        System.out.println("Total ways to reach " + n + "th stair: " + countWays(n, m));
    }
}
