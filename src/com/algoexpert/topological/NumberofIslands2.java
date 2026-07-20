package com.algoexpert.topological;

import java.util.ArrayList;
import java.util.List;

public class NumberofIslands2 {

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        int[][] dirs = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        UnionFind unionFind = new UnionFind(m * n);
        boolean[][] land = new boolean[m][n];
        int islands = 0;

        for(int[] pos : positions) {
            int r = pos[0];
            int c = pos[1];

            if(land[r][c]) {
                ans.add(islands);
                continue;
            }

            land[r][c] = true;
            islands++;

            int curr = r * n + c;

            for(int[] dir: dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                    continue;

                if (!land[nr][nc])
                    continue;

                int neighbour = nr * n + nc;

                if(unionFind.unionByRank(curr, neighbour)) {
                    islands--;
                }
            }
            ans.add(islands);
        }

        return ans;
    }

    public static void main(String[] args) {

        NumberofIslands2 sol = new NumberofIslands2();

        int m = 3;
        int n = 3;

        int[][] positions = {
                {0, 0},
                {0, 1},
                {1, 2},
                {2, 1},
                {1, 1}
        };
        List<Integer> result = sol.numIslands2(m, n, positions);
        System.out.println(result);
    }
}
