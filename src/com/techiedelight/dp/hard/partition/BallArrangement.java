package com.techiedelight.dp.hard.partition;

import java.util.HashMap;
import java.util.Map;

public class BallArrangement {

    static Map<String, Long> memo = new HashMap<>();

    public static long countWays(int r, int g, int b, char prev) {
        if (r == 0 && g == 0 && b == 0) return 1;

        String key = r + "," + g + "," + b + "," + prev;
        if (memo.containsKey(key)) return memo.get(key);

        long ways = 0;
        if (r > 0 && prev != 'R') {
            ways += countWays(r - 1, g, b, 'R');
        }

        if (g > 0 && prev != 'G') {
            ways += countWays(r, g - 1, b, 'G');
        }
        // Choose blue
        if (b > 0 && prev != 'B') {
            ways += countWays(r, g, b - 1, 'B');
        }
        memo.put(key, ways);
        return ways;
    }

    public static void main(String[] args) {
        int r = 2, g = 3, b = 1;
        int total = r + g + b;

        // Feasibility check: no color should be more than (n + 1) / 2
        int maxCount = Math.max(r, Math.max(g, b));
        if (maxCount > (total + 1) / 2) {
            System.out.println("No valid arrangements possible.");
        } else {
            long result = countWays(r, g, b, ' ');
            System.out.println("Total valid arrangements: " + result);
        }
    }
}
