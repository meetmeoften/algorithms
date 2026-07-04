package com.algoexpert.topological;

public class UnionFind {

    int[] parent;
    int[] rank;
    int[] size;

    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int node) {
        if(parent[node] == node) {
            return node;
        }
        parent[node] = find(parent[node]);
        return parent[node];
    }

    public boolean unionByRank(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) return false;

        if(rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if(rank[rootX] < rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }

    public boolean unionBySize(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY) return false;
        if(size[rootX] < size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        } else {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }
        return true;
    }

    public int getSize(int x) {
        return size[find(x)];
    }

}
