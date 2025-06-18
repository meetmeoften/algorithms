package com.techiedelight.dp.hard.lcs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindAllLCS {

    // Function to build the LCS length DP table
    private static int[][] buildDP(String X, String Y) {
        int m = X.length();
        int n = Y.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp;
    }

    // Function to find all LCS sequences using backtracking and memoization
    private static Set<String> findAllLCS(String X, String Y, int[][] dp, int i, int j, Map<String, Set<String>> memo) {
        if (i == 0 || j == 0) {
            Set<String> base = new HashSet<>();
            base.add("");
            return base;
        }

        String key = i + "|" + j;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        Set<String> result = new HashSet<>();

        if (X.charAt(i - 1) == Y.charAt(j - 1)) {
            for (String lcs : findAllLCS(X, Y, dp, i - 1, j - 1, memo)) {
                result.add(lcs + X.charAt(i - 1));
            }
        } else {
            if (dp[i - 1][j] >= dp[i][j - 1]) {
                result.addAll(findAllLCS(X, Y, dp, i - 1, j, memo));
            }
            if (dp[i][j - 1] >= dp[i - 1][j]) {
                result.addAll(findAllLCS(X, Y, dp, i, j - 1, memo));
            }
        }
        memo.put(key, result);
        return result;
    }

    public static void main(String[] args) {
        String X = "abcabcaa";
        String Y = "acbacba";

        int[][] dp = buildDP(X, Y);
        int m = X.length(), n = Y.length();

        Set<String> allLCS = findAllLCS(X, Y, dp, m, n, new HashMap<>());

        System.out.println("Length of LCS: " + dp[m][n]);
        System.out.println("All LCS strings:");
        for (String lcs : allLCS) {
            System.out.println(lcs);
        }
    }
}