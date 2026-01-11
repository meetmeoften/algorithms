package com.algoexpert.graph;

import java.util.ArrayList;
import java.util.List;

public class MaximumSubGraphScoreInTree {

    /**
     * https://leetcode.com/problems/maximum-subgraph-score-in-a-tree/solutions/7397021/savis-most-optimal-solution-java-python-3k27o/
     */

    List<Integer>[] g;
        int[] val;
        int[] dp;
        int[] res;

        public int[] maxSubgraphScore(int n, int[][] edges, int[] good) {
            g = new ArrayList[n];
            for(int i=0; i < n; i++) g[i] = new ArrayList<>();
            for(int[] e : edges) {
                g[e[0]].add(e[1]);
                g[e[1]].add(e[0]);
            }

            val = new int[n];
            for(int i=0; i < n; i++) val[i] = good[i] == 1 ? 1 : -1;

            dp = new int[n];
            res = new int[n];

            dfs1(0, -1);
            dfs2(0, -1, 0);
            return res;
        }
        //Bottom-up
        private void dfs1(int u, int p){
            dp[u] = val[u];
            for(int v : g[u])  {
                if(v == p) continue;
                dfs1(v, u);
                dp[u] += Math.max(0, dp[v]);
            }
        }

        private void dfs2(int u, int p, int parContribution){
            res[u] = dp[u] + parContribution;
            for(int v : g[u]) if(v != p){
                int removeChild = Math.max(0, dp[v]);
                int childParContribution = Math.max(0, res[u] - removeChild);
                dfs2(v, u, childParContribution);
            }
        }

    public static void main(String[] args) {

        MaximumSubGraphScoreInTree game = new MaximumSubGraphScoreInTree();
        game.maxSubgraphScore(3, new int[][]{{0,1},{1,2}}, new int[]{1, 0, 1});
        //game.maxSubgraphScore(5, new int[][]{{1,0}, {1,2}, {1,3}, {3, 4}}, new int[]{0, 1, 0, 1, 1});
    }
}
