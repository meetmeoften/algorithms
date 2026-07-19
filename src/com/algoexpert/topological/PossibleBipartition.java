package com.algoexpert.topological;

import java.util.*;

public class PossibleBipartition {

    public boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] di : dislikes) {
           map.putIfAbsent(di[0], new ArrayList<>());
           map.putIfAbsent(di[1], new ArrayList<>());

           map.get(di[0]).add(di[1]);
           map.get(di[1]).add(di[0]);
        }

        int[] color  = new int[n+1];
        Arrays.fill(color, -1);
        for(int i= 1; i <= n; i++) {
            if(color[i] == -1) {
                color[i] = 0;
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                while(!q.isEmpty()) {
                    int curr = q.poll();
                    for(int node : map.get(curr)) {
                        if(color[node] == -1) {
                            color[node] = 1 - color[curr];
                            q.offer(node);
                        } else {
                            if(color[node] == color[curr]) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
