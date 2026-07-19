package com.algoexpert.topological;

import java.util.ArrayList;
import java.util.List;

public class AccountsMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        List<List<String>> result = new ArrayList<>();





        return result;
    }


    public class UnionFind {
        int[] parent;

        UnionFind(int n) {
            parent = new int[n];
            for(int i= 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if(parent[x] == x) {
                return parent[x];
            }
            return find(parent[x]);
        }

        public void union(int a, int b) {
            parent[a] = b;
        }
    }
}
