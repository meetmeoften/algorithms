package com.techiedelight.dp.medium.lcs;

public class LCS {


    static int lcs(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return 1 + lcs(s1, s2, m - 1, n - 1);
        }
        return Math.max(lcs(s1, s2, m - 1, n), lcs(s1, s2, m, n - 1));
    }

    // Function to print the Longest Common Subsequence (LCS) using the DP table
    public static String printLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // DP table to store lengths of LCS
        int[][] dp = new int[m + 1][n + 1];

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // Now backtrack to find the LCS string
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            // If characters match, add them to the result and move diagonally in the table
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            }
            // If not, move in the direction of the larger value (up or left)
            else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        // The LCS is built in reverse, so reverse it before returning
        return lcs.reverse().toString();
    }

    public static String printLcs(String s1, String s2, int m, int n, int[][] dp) {
        if (m == 0 || n == 0) return "";

        if (dp[m][n] != -1) return "";

        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            dp[m][n] = 1;
            return printLcs(s1, s2, m - 1, n - 1, dp) + s1.charAt(m - 1);
        } else {
            String left = printLcs(s1, s2, m - 1, n, dp);
            String right = printLcs(s1, s2, m, n - 1, dp);
            return (left.length() > right.length()) ? left : right;
        }
    }

    public static void main(String[] args) {
        String str1 = "AGGTAB";
        String str2 = "GXTXAYB";
        int lcsLength = lcs(str1, str2, str1.length(), str2.length());
    }
}
