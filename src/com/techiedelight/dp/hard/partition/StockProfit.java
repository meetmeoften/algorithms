package com.techiedelight.dp.hard.partition;

public class StockProfit {

    public static int dfs(int[] prices, int buySell, int ind) {
        if (ind >= prices.length) {
            return 0;
        }
        int profit = 0;
        if (buySell == 0) {
            profit = Math.max(dfs(prices, 0, ind + 1),
                    -prices[ind] + dfs(prices, 1, ind + 1));
        }
        if (buySell == 1) {
            profit = Math.max(dfs(prices, 1, ind + 1),
                    prices[ind] + dfs(prices, 0, ind + 1));
        }
        return profit;
    }

    public static void main(String[] args) {
        int result = dfs(new int[]{2, 1, 7, 6}, 0, 0);
        System.out.println(result);
    }
}
