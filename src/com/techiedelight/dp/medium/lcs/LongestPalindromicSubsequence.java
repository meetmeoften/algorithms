package com.techiedelight.dp.medium.lcs;

public class LongestPalindromicSubsequence {

    public static int lps(String s, int start, int end) {
        if (start > end) {
            return 0;
        }

        if (start == end) {
            return 1;
        }

        if (s.charAt(start) == s.charAt(end)) {
            return 2 + lps(s, start + 1, end - 1);
        } else {
            return Math.max(lps(s, start, end - 1), lps(s, start + 1, end));
        }
    }

    public static void main(String[] args) {
        String s = "bbab";
        int result = lps(s, 0, s.length() - 1);
        System.out.println("Length of Longest Palindromic Subsequence: " + result);
    }
}
