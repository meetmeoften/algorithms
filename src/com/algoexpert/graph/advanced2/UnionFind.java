package com.algoexpert.graph.advanced2;

public class UnionFind {

    private int[] parent;
    private int[] rank;
    // size also would be there
    int count;

    public UnionFind(int n) {
        parent = new int[n+1];
        rank = new int[n+1];

        for(int i=1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) {
            return;
        }

        if(rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if(rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    // Check if two elements are in the same set
    public boolean connected(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);

        uf.union(1, 2);
        uf.union(2, 3);
        uf.union(4, 5);

        System.out.println(uf.connected(1, 3)); // true
        System.out.println(uf.connected(1, 4)); // false

        uf.union(3, 4);
        System.out.println(uf.connected(1, 5)); // true
    }
}
