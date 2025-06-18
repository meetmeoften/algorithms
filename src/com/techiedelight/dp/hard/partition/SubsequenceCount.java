package com.techiedelight.dp.hard.partition;

import java.util.HashMap;
import java.util.Map;

public class SubsequenceCount {

    static Map<String, Integer> memo = new HashMap<>();

    public static int countSubsequences(String str, String pattern) {
        memo.clear(); // Reset between calls
        return helper(str, pattern, 0, 0);
    }


    private static int helper(String str, String pattern, int i, int j) {
        if (j == pattern.length()) return 1;
        if (i == str.length()) return 0;
        // Memo key
        String key = i + "," + j;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int count = 0;
        if (str.charAt(i) == pattern.charAt(j)) {
            // Use current char + skip current char
            count = helper(str, pattern, i + 1, j + 1) + helper(str, pattern, i + 1, j);
        } else {
            // Skip current char
            count = helper(str, pattern, i + 1, j);
        }
        memo.put(key, count);
        return count;
    }

    public static int countSubsequences2(String s, String p) {
        int m = s.length();
        int n = p.length();
        int[][] dp = new int[m + 1][n + 1];

        // Initialization
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1; // Empty pattern
        }

        // Fill dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String s = "babgbag";
        String p = "bag";

        int count = countSubsequences(s, p);
        System.out.println("Number of times '" + p + "' appears in '" + s + "' as a subsequence: " + count);
    }

}
