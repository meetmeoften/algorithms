package com.techiedelight.dp.medium.lcs;

public class LongestCommonSubstring {

    public static String longestCommonSubstring(String s1, String s2) {

        int maxLength = 0;
        int endIndex = 0;

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i;
                    }
                }
            }
        }

        return s1.substring(endIndex - maxLength, endIndex);
    }


    static int maxLength = 0;

    public static int lcsRecursive(String s1, String s2, int i, int j, int count) {
        if (i == 0 || j == 0)
            return count;
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            count = lcsRecursive(s1, s2, i - 1, j - 1, count + 1);
            if (count > maxLength) {
                maxLength = count;
            }
        }
        lcsRecursive(s1, s2, i, j - 1, 0);
        lcsRecursive(s1, s2, i - 1, j, 0);
        return maxLength;
    }

    public static void main(String[] args) {
        String s1 = "ABABC";
        String s2 = "BABCAC";

        String result = longestCommonSubstring(s1, s2);
        System.out.println("Longest Common Substring: " + result);
    }
}
