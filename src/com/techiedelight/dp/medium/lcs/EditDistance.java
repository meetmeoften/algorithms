package com.techiedelight.dp.medium.lcs;

public class EditDistance {

    public static int editDistance(String s1, String s2, int m, int n) {
        if (m == 0) return n;
        if (n == 0) return m;

        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return editDistance(s1, s2, m - 1, n - 1);
        }
        // If last characters are different, consider all three operations:
        int insertOp = editDistance(s1, s2, m, n - 1);    // Insert
        int deleteOp = editDistance(s1, s2, m - 1, n);    // Delete
        int replaceOp = editDistance(s1, s2, m - 1, n - 1); // Replace
        return 1 + Math.min(replaceOp, Math.min(insertOp, deleteOp));

    }

    public static int minEditDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Create a DP table
        int[][] dp = new int[m + 1][n + 1];

        // Initialize the table
        for (int i = 0; i <= m; i++) dp[i][0] = i; // deletions
        for (int j = 0; j <= n; j++) dp[0][j] = j; // insertions

        // Fill the table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // no operation
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j - 1], // replace
                            Math.min(dp[i - 1][j],  // delete
                                    dp[i][j - 1]) // insert
                    );
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String s1 = "sunday";
        String s2 = "saturday";

        int distance = editDistance(s1, s2, s1.length(), s2.length());
        System.out.println("Edit Distance: " + distance);
    }
}
