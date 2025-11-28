package com.google.graph;

import com.zjava8.java.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReorderRoutesToZero {

    public int minReorder(int n, List<List<Integer>> connections) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(i, new ArrayList<>());

        for (List<Integer> conn : connections) {
            int from = conn.get(0);
            int to = conn.get(1);
            map.get(from).add(new int[]{to, 1});
            map.get(to).add(new int[]{from, 0});
        }
        return dfs(0, -1, map);
    }

    private int dfs(int node, int parent, Map<Integer, List<int[]>> graph) {
        int count = 0;
        for (int[] neighbor : graph.get(node)) {
            int next = neighbor[0], needsReverse = neighbor[1];
            if (next != parent) {
                count += needsReverse;
                count += dfs(next, node, graph);
            }
        }
        return count;
    }
}
