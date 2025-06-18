package com.techiedelight.dp.medium.lcs;

public class DiffUtility {

    public static void findLcsAndComputeDiff(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        printDiff(str1, str2, dp);
    }

    // Function to print the diff using the LCS table
    private static void printDiff(String X, String Y, int[][] lcs) {
        int i = X.length();
        int j = Y.length();
        StringBuilder result = new StringBuilder();
        while (i > 0 && j > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                result.insert(0, X.charAt(i - 1) + " ");
                i--;
                j--;
            } else if (lcs[i - 1][j] >= lcs[i][j - 1]) {
                result.insert(0, "-" + X.charAt(i - 1) + " ");
                i--;
            } else {
                result.insert(0, "+" + Y.charAt(j - 1) + " ");
                j--;
            }
        }
        // Remaining characters in X
        while (i > 0) {
            result.insert(0, "-" + X.charAt(i - 1) + " ");
            i--;
        }
        // Remaining characters in Y
        while (j > 0) {
            result.insert(0, "+" + Y.charAt(j - 1) + " ");
            j--;
        }
        System.out.println(result.toString());
    }

    // brute Force - Method to print the differences between two strings
    public static void printDiff(String X, String Y) {
        int i = 0, j = 0;
        while (i < X.length() && j < Y.length()) {
            // If characters are the same, print them as is
            if (X.charAt(i) == Y.charAt(j)) {
                System.out.print(X.charAt(i) + " ");
                i++;
                j++;
            }
            // If characters are different, handle deletions and insertions
            else {
                // Deletion from Y
                System.out.print("-" + Y.charAt(j) + " ");
                j++;
            }
        }

        // If there are remaining characters in X, treat them as deletions
        while (i < X.length()) {
            System.out.print("-" + X.charAt(i) + " ");
            i++;
        }

        // If there are remaining characters in Y, treat them as insertions
        while (j < Y.length()) {
            System.out.print("+" + Y.charAt(j) + " ");
            j++;
        }
    }

    public static void main(String[] args) {
        // Input strings
        String X = "XMJYAUZ";
        String Y = "XMJAATZ";

        // Print the differences
        printDiff(X, Y);
    }
}
