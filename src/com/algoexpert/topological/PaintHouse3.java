package com.algoexpert.topological;

public class PaintHouse3 {
    private static final int INF = 1_000_000_000;
    private int[] houses;
    private int[][] cost;
    private int m, n, target;
    private Integer[][][] memo;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        this.houses = houses;
        this.cost = cost;
        this.m = m;
        this.n = n;
        this.target = target;
        // house, prevColor(0..n), neighborhoods
        memo = new Integer[m][n + 1][target + 1];
        int ans = dfs(0, 0, 0);
        return ans >= INF ? -1 : ans;
    }

    private int dfs(int idx, int prevColor, int groups) {
        if (groups > target)
            return INF;
        if (idx == m)
            return groups == target ? 0 : INF;
        if (memo[idx][prevColor][groups] != null)
            return memo[idx][prevColor][groups];
        int ans = INF;
        if (houses[idx] != 0) {
            int color = houses[idx];
            int newGroups = groups;
            if (color != prevColor)
                newGroups++;
            ans = dfs(idx + 1, color, newGroups);

        } else {
            for (int color = 1; color <= n; color++) {
                int newGroups = groups;
                if (color != prevColor)
                    newGroups++;
                ans = Math.min(ans, cost[idx][color - 1] + dfs(idx + 1, color, newGroups));
            }
        }
        return memo[idx][prevColor][groups] = ans;
    }

    public static void main(String[] args) {

        int[] houses = {0, 0, 0, 0, 0};

        int[][] cost = {
                {1, 10},
                {10, 1},
                {10, 1},
                {1, 10},
                {5, 1}
        };
        int m = 5;
        int n = 2;
        int target = 3;

        PaintHouse3 sol = new PaintHouse3();
        System.out.println(sol.minCost(houses, cost, m, n, target));
    }
}
