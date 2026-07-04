package com.algoexpert2.arrays.veryhard;

import java.util.*;

class MaxPointsOnLine {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;

        int ans = 2;

        for (int i = 0; i < n; i++) {
            Map<String, Integer> map = new HashMap<>();
            int max = 0;

            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                int gcd = gcd(dx, dy);

                dx /= gcd;
                dy /= gcd;

                // normalize slope
                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                }

                if (dx == 0) {
                    dy = 1;   // vertical line
                }

                if (dy == 0) {
                    dx = 1;   // horizontal line
                }

                String key = dy + "/" + dx;

                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }

            ans = Math.max(ans, max + 1);
        }

        return ans;
    }

    private int gcd(int a, int b) {
        if (b == 0) return Math.abs(a);
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        int[][] input = new int[][] { { 1, 1 }, { 2, 2 }, { 3, 3 } };
        MaxPointsOnLine maxPointsOnLine = new MaxPointsOnLine();
        maxPointsOnLine.maxPoints(input);
    }
}
