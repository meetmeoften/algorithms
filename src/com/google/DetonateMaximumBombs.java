package com.google;

import java.util.LinkedList;
import java.util.Queue;

public class DetonateMaximumBombs {

    public int maximumDetonation(int[][] bombs) {
        boolean[] visited;
        int max = 0;
        for (int i = 0; i < bombs.length; i++) {
            visited = new boolean[bombs.length];
            int val = dfs(bombs, visited, i);
            max = Math.max(val, max);
        }
        return max;
    }

    public int dfs(int[][] bombs, boolean[] visited, int i) {
        visited[i] = true;
        int diff = 1;
        for (int j = 0; j < bombs.length; j++) {
            if (!visited[j] && isInRange(bombs[i], bombs[j])) {
                diff += dfs(bombs, visited, j);
            }
        }
        return diff;
    }

    public int getMaxBfs(int[][] bombs, int i) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] seen = new boolean[bombs.length];
        seen[i] = true;
        q.offer(i);
        int count = 1;
        while(!q.isEmpty()) {
            int curr = q.poll();
            for(int j=0; j < bombs.length; j++){
                if(!seen[j] && isInRange(bombs[curr], bombs[j])) {
                    seen[j] = true;
                    count++;
                    q.offer(j);
                }
            }
        }
        return count;
    }

    private boolean isInRange(int[] a, int[] b) {
        long dx = a[0] - b[0];
        long dy = a[1] - b[1];
        long distanceSquared = dx * dx + dy * dy;
        long radiusSquared = (long) a[2] * a[2];
        return distanceSquared <= radiusSquared;
    }

    public static void main(String[] args) {
        int[][] bombs = new int[][]{{2, 1, 3}, {6, 1, 4}};
        new DetonateMaximumBombs().maximumDetonation(bombs);
    }

}
