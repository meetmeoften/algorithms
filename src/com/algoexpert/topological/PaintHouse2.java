package com.algoexpert.topological;

public class PaintHouse2 {
    private int[][] costs;
    private Integer[][] memo;
    private int n, k;

    public int minCostIIMemo(int[][] costs) {
        this.costs = costs;
        n = costs.length;
        if (n == 0)
            return 0;
        k = costs[0].length;
        // prevColor ranges from -1 to k-1
        // Store prevColor + 1 as index
        memo = new Integer[n][k + 1];
        return dfs(0, -1);
    }

    private int dfs(int house, int prevColor) {
        if (house == n)
            return 0;
        if (memo[house][prevColor + 1] != null)
            return memo[house][prevColor + 1];
        int ans = Integer.MAX_VALUE;
        for (int color = 0; color < k; color++) {
            if (color == prevColor)
                continue;
            ans = Math.min(ans, costs[house][color] + dfs(house + 1, color));
        }
        return memo[house][prevColor + 1] = ans;
    }

    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0)
            return 0;
        int n = costs.length;
        int k = costs[0].length;
        int[] dp = new int[k];
        for (int j = 0; j < k; j++)
            dp[j] = costs[0][j];
        for (int i = 1; i < n; i++) {
            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < k; j++) {
                if (dp[j] < min1) {
                    min2 = min1;
                    min1 = dp[j];
                    minIndex = j;
                } else if (dp[j] < min2) {
                    min2 = dp[j];
                }
            }
            int[] newDp = new int[k];
            for (int j = 0; j < k; j++) {
                if (j == minIndex)
                    newDp[j] = costs[i][j] + min2;
                else
                    newDp[j] = costs[i][j] + min1;
            }
            dp = newDp;
        }
        int ans = Integer.MAX_VALUE;
        for (int x : dp)
            ans = Math.min(ans, x);
        return ans;
    }

    public static void main(String[] args) {
        int[][] costs = {
                {1, 5, 3},
                {2, 9, 5}
        };
        PaintHouse2 obj = new PaintHouse2();
        System.out.println(obj.minCostII(costs));
    }
}
