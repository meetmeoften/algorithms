package com.algoexpert.graph.advanced2;

import java.util.*;

public class IslandBridgeBuilder {
    static int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    static int N, M;

    public static List<int[][]> connectIslands(char[][] map) {
        N = map.length;
        M = map[0].length;
        int[][] islandMap = new int[N][M];
        int islandId = 1;

        // Step 1: Label all islands with unique ids using DFS
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'x' && islandMap[i][j] == 0) {
                    dfsLabel(map, islandMap, i, j, islandId++);
                }
            }
        }

        // Step 2: Find all possible bridges between islands
        List<Bridge> bridges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (islandMap[i][j] != 0) {
                    int fromIsland = islandMap[i][j];
                    for (int[] dir : directions) {
                        int x = i + dir[0], y = j + dir[1], length = 0;
                        while (isInBounds(x, y) && islandMap[x][y] == 0) {
                            x += dir[0];
                            y += dir[1];
                            length++;
                        }
                        if (length > 0 && isInBounds(x, y) && islandMap[x][y] != fromIsland) {
                            int toIsland = islandMap[x][y];
                            bridges.add(new Bridge(length, new int[]{i,j}, new int[]{x,y}, fromIsland, toIsland));
                        }
                    }
                }
            }
        }

        // Step 3: Use Kruskal's algorithm to select bridges that connect all islands
        Collections.sort(bridges); // by length
        UnionFind uf = new UnionFind(islandId);
        List<int[][]> result = new ArrayList<>();

        for (Bridge b : bridges) {
            if (uf.union(b.fromIsland, b.toIsland)) {
                result.add(new int[][]{b.start, b.end});
            }
        }

        return result;
    }

    private static void dfsLabel(char[][] map, int[][] islandMap, int x, int y, int id) {
        islandMap[x][y] = id;
        for (int[] dir : directions) {
            int nx = x + dir[0], ny = y + dir[1];
            if (isInBounds(nx, ny) && map[nx][ny] == 'x' && islandMap[nx][ny] == 0) {
                dfsLabel(map, islandMap, nx, ny, id);
            }
        }
    }

    private static boolean isInBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    // Bridge class
    static class Bridge implements Comparable<Bridge> {
        int length;
        int[] start, end;
        int fromIsland, toIsland;

        Bridge(int length, int[] start, int[] end, int fromIsland, int toIsland) {
            this.length = length;
            this.start = start;
            this.end = end;
            this.fromIsland = fromIsland;
            this.toIsland = toIsland;
        }

        public int compareTo(Bridge other) {
            return Integer.compare(this.length, other.length);
        }
    }

    // Union-Find class
    static class UnionFind {
        int[] parent;

        UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return false;
            parent[rootY] = rootX;
            return true;
        }
    }

    // Example
    public static void main(String[] args) {
        char[][] map = {
                {'.','x','x','.','.','.','.'},
                {'.','.','x','.','.','.','x'},
                {'.','.','x','x','x','.','x'},
                {'.','.','.','.','.','.','.'},
                {'.','.','.','.','.','x','x'}
        };

        List<int[][]> bridges = connectIslands(map);
        System.out.println("Bridges:");
        for (int[][] bridge : bridges) {
            System.out.println(Arrays.deepToString(bridge));
        }
    }
}

