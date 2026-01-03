package com.algoexpert.binarytree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeLifting {

    static int N;               // number of nodes
    static int LOG;             // log2(N)
    static List<Integer>[] tree;
    static int[][] up;          // up[v][i] = 2^i-th ancestor of v
    static int[] depth;

    // ---------- Initialization ----------
    static void init(int n) {
        N = n;
        LOG = 1;
        while ((1 << LOG) <= N) LOG++;

        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        up = new int[N][LOG];
        depth = new int[N];
    }

    // ---------- Add Edge ----------
    static void addEdge(int u, int v) {
        tree[u].add(v);
        tree[v].add(u);
    }

    // ---------- DFS Preprocessing ----------
    static void dfs(int v, int p) {
        up[v][0] = p;

        for (int i = 1; i < LOG; i++) {
            if (up[v][i - 1] == -1)
                up[v][i] = -1;
            else
                up[v][i] = up[up[v][i - 1]][i - 1];
        }

        for (int to : tree[v]) {
            if (to != p) {
                depth[to] = depth[v] + 1;
                dfs(to, v);
            }
        }
    }

    // ---------- k-th Ancestor ----------
    static int kthAncestor(int v, int k) {
        for (int i = 0; i < LOG; i++) {
            if ((k & (1 << i)) != 0) {
                v = up[v][i];
                if (v == -1) return -1;
            }
        }
        return v;
    }

    // ---------- LCA ----------
    static int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // Lift u up to same depth as v
        u = kthAncestor(u, depth[u] - depth[v]);
        if (u == v) return u;

        // Lift both up while ancestors differ
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }

        // Parent is LCA
        return up[u][0];
    }

    // ---------- Main (Example Usage) ----------
    public static void main(String[] args) {

        /*
            Example Tree:
                    0
                   / \
                  1   2
                 / \
                3   4
               /
              5
             /
            6
        */

        init(7);

        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(1, 3);
        addEdge(1, 4);
        addEdge(5, 3);
        addEdge(5, 6);

        // Root the tree at node 0
        depth[0] = 0;
        dfs(0, -1);

        // Example Queries
        System.out.println(lca(5, 4));
        System.out.println(lca(3, 4));        // Output: 1
        System.out.println(lca(2, 4));        // Output: 0
        System.out.println(kthAncestor(4, 1)); // Output: 1
        System.out.println(kthAncestor(4, 2)); // Output: 0
        System.out.println(kthAncestor(4, 3)); // Output: -1
    }

}
