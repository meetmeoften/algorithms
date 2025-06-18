package com.techiedelight.dp.medium.lcs;

public class LCSKSequence {


    public static String findLCS(String[] sequences) {
        if (sequences == null || sequences.length == 0) {
            return "";
        }

        // If only one sequence, the LCS is the sequence itself
        if (sequences.length == 1) {
            return sequences[0];
        }

        // Start with LCS of first two sequences
        String result = findLCSOfTwo(sequences[0], sequences[1]);

        // For each remaining sequence, find LCS with current result
        for (int i = 2; i < sequences.length; i++) {
            result = findLCSOfTwo(result, sequences[i]);

            // If at any point LCS becomes empty, we can stop
            if (result.isEmpty()) {
                return "";
            }
        }

        return result;
    }

    /**
     * Finds the longest common subsequence of two strings
     *
     * @param s1 First string
     * @param s2 Second string
     * @return The longest common subsequence
     */
    private static String findLCSOfTwo(String s1, String s2) {
        if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty()) {
            return "";
        }

        int m = s1.length();
        int n = s2.length();

        // dp[i][j] represents length of LCS of s1[0...i-1] and s2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Fill dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Reconstruct the LCS
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        // Reverse to get the correct order
        return lcs.reverse().toString();
    }

    public static void main(String[] args) {
        // Example usage
        String[] sequences = {
                "ABCDEFG",
                "ABCXYZ",
                "ABCPQR"
        };

        String lcs = findLCS(sequences);
        System.out.println("Longest Common Subsequence: " + lcs);

        // More examples
        String[] sequences2 = {
                "AGGTAB",
                "GXTXAYB",
                "GTAB"
        };

        String lcs2 = findLCS(sequences2);
        System.out.println("Longest Common Subsequence: " + lcs2);
    }

    // sample for 3 sequence
    public static int lcs3(String a, String b, String c, int i, int j, int k) {
        if (i == 0 || j == 0 || k == 0) return 0;

        if (a.charAt(i - 1) == b.charAt(j - 1) && a.charAt(i - 1) == c.charAt(k - 1)) {
            return 1 + lcs3(a, b, c, i - 1, j - 1, k - 1);
        } else {
            return Math.max(
                    Math.max(lcs3(a, b, c, i - 1, j, k), lcs3(a, b, c, i, j - 1, k)),
                    lcs3(a, b, c, i, j, k - 1)
            );
        }
    }
}
