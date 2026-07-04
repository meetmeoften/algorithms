package com.algoexpert.topological;

import java.util.ArrayList;
import java.util.List;

public class BestMeetingPoint {

    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();

        int m = grid.length;
        int n = grid[0].length;

        // rows are collected in sorted order
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                }
            }
        }

        // cols are collected in sorted order
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) {
                    cols.add(j);
                }
            }
        }

        return getDistance(rows) + getDistance(cols);
    }

    private int getDistance(List<Integer> points) {
        int left = 0;
        int right = points.size() - 1;
        int distance = 0;

        while (left < right) {
            distance += points.get(right) - points.get(left);
            left++;
            right--;
        }

        return distance;
    }

    public static void main(String[] args) {
        BestMeetingPoint sol = new BestMeetingPoint();

        int[][] grid = {
                {1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        };

        System.out.println(sol.minTotalDistance(grid));
        // Output: 6
    }
}
