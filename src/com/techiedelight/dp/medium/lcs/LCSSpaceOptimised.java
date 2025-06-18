package com.techiedelight.dp.medium.lcs;

public class LCSSpaceOptimised {

    public static int lcsLength(String s1, String s2) {
        // To optimize space usage, ensure s1 is the shorter string
        if (s1.length() > s2.length()) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }

        int m = s1.length();
        int n = s2.length();

        // Use only two rows of space - current and previous
        int[] prevRow = new int[m + 1];
        int[] currRow = new int[m + 1];

        // Fill the dp table
        for (int j = 1; j <= n; j++) {
            // Swap current and previous rows
            int[] temp = prevRow;
            prevRow = currRow;
            currRow = temp;

            for (int i = 1; i <= m; i++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    currRow[i] = prevRow[i - 1] + 1;
                } else {
                    currRow[i] = Math.max(prevRow[i], currRow[i - 1]);
                }
            }
        }

        // Return the length of LCS
        return currRow[m];
    }
}
