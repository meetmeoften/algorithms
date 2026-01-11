package com.algoexpert2.binarytree;

import java.util.ArrayList;
import java.util.List;

public class DiffBetweenMaxAndMinPriceSum {

    private long ans = 0;
    private List<Integer>[] tree;
    private int[] price;

    public long maxOutput(int n, int[][] edges, int[] price) {
        this.price = price;
        tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }

        dfs(0, -1);
        return ans;
    }

    // returns [maxDown, minDown]
    private long[] dfs(int u, int parent) {
        long maxDown = price[u];
        long minDown = price[u];

        for (int v : tree[u]) {
            if (v == parent) continue;

            long[] child = dfs(v, u);

            maxDown = Math.max(maxDown, price[u] + child[0]);
            minDown = Math.min(minDown, price[u] + child[1]);
        }

        ans = Math.max(ans, maxDown - minDown);
        return new long[]{maxDown, minDown};
    }

    public static void main(String[] args) {

        DiffBetweenMaxAndMinPriceSum obj = new DiffBetweenMaxAndMinPriceSum();
        int n = 3;
        int[][] edges = {{0, 1}, {1, 2}};
        int[] price = {2, 1, 1};
        long ans = obj.maxOutput(n, edges, price);
        System.out.println(ans);
    }

}
