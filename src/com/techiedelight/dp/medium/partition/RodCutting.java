package com.techiedelight.dp.medium.partition;

public class RodCutting {

    public static int rodCut(int[] price, int n) {
        if (n == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int cost = price[i - 1] + rodCut(price, n - i);

            if (cost > max) {
                max = cost;
            }
        }
        return max;
    }

    public static int rodCutting(int[] price, int n) {
        // dp[i] stores the maximum profit for a rod of length i
        int[] dp = new int[n + 1];

        // Build the dp array
        for (int i = 1; i <= n; i++) {
            int maxProfit = Integer.MIN_VALUE;

            // Try cutting the rod into pieces of various lengths
            for (int j = 1; j <= i; j++) {
                maxProfit = Math.max(maxProfit, price[j - 1] + dp[i - j]);
            }

            dp[i] = maxProfit; // Store the result for rod of length i
        }

        return dp[n]; // The maximum profit for rod of length n
    }


    public static int rodCut(int[] price, int i, int n) {
        if (n == 0) return 0;       // no rod left
        if (i < 0) return Integer.MIN_VALUE;
        // not take
        int notTake = rodCut(price, i - 1, n);
        // take
        int take = price[i] + rodCut(price, i, n - (i + 1));
        return Math.max(take, notTake);
    }


    public static void main(String[] args) {
        int price[] = {1, 5, 8, 9, 10, 17, 17, 20};
        // rod length
        int n = 3;
        System.out.println("Profit is " + rodCut(price, n));
        System.out.println("Profit is " + rodCutting(price, n));
    }
}
