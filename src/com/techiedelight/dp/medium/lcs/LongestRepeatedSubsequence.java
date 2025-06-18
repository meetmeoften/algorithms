package com.techiedelight.dp.medium.lcs;

public class LongestRepeatedSubsequence {


    /**
     * The idea is to find the LCS of the given string with itself, i.e., call LCS(X, X) and exclude the cases when
     * indexes are the same (i = j) since repeated characters hold a different index in the input string.
     * @param s
     * @param i
     * @param j
     * @return
     */

    public static int lrsRecursive(String s, int i, int j) {
        if (i == 0 || j == 0) {
            return 0;
        }
        if (s.charAt(i - 1) == s.charAt(j - 1) && i != j) {
            return 1 + lrsRecursive(s, i - 1, j - 1);
        } else {
            return Math.max(lrsRecursive(s, i - 1, j), lrsRecursive(s, i, j - 1));
        }
    }

    public static void main(String[] args) {
        String s = "aabb";
        int result = lrsRecursive(s, s.length(), s.length()); // Start recursion from the end of the string
        System.out.println("Length of Longest Repeated Subsequence: " + result);
    }
}
