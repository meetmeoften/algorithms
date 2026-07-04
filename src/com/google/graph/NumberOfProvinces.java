package com.google.graph;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] visited = new int[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                dfs(isConnected, i, visited, n);
                count++;
            }
        }
        return count;
    }

    void dfs(int[][] grid, int u, int[] visited, int n) {
        visited[u] = 1;
        for (int i = 0; i < n; i++) {
            if (grid[u][i] == 1 && visited[i] == 0)
                dfs(grid, i, visited, n);
        }
    }


    public int findCircleNum2(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                visited[i] = true;
                while (!queue.isEmpty()) {
                    int city = queue.poll();
                    for (int j = 0; j < n; j++) {
                        if (isConnected[city][j] == 1 && !visited[j]) {
                            queue.offer(j);
                            visited[j] = true;
                        }
                    }
                }
                provinces++;
            }
        }
        return provinces;
    }

    public static void main(String[] args) {
        int[][] isConnected = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        NumberOfProvinces solution = new NumberOfProvinces();
        int result = solution.findCircleNum(isConnected);
        System.out.println("Number of Provinces: " + result); // Output: 2
    }
}


