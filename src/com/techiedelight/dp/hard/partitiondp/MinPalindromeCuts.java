package com.techiedelight.dp.hard.partitiondp;

import java.util.*;

public class MinPalindromeCuts {
    private int[] memo;
    private Boolean[][] palindromeMemo;

    public int minCut(String s) {
        int n = s.length();
        memo = new int[n];
        Arrays.fill(memo, -1);
        palindromeMemo = new Boolean[n][n];
        return minCutsFrom(s, 0) - 1; // -1 because we count partitions, not cuts
    }

    private int minCutsFrom(String s, int start) {
        if (start == s.length()) return 0;
        if (memo[start] != -1) return memo[start];

        int minCuts = Integer.MAX_VALUE;

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                int cuts = 1 + minCutsFrom(s, end + 1);
                minCuts = Math.min(minCuts, cuts);
            }
        }

        memo[start] = minCuts;
        return minCuts;
    }

    private boolean isPalindrome(String s, int i, int j) {
        if (i >= j) return true;
        if (palindromeMemo[i][j] != null) return palindromeMemo[i][j];

        palindromeMemo[i][j] = (s.charAt(i) == s.charAt(j)) && isPalindrome(s, i + 1, j - 1);
        return palindromeMemo[i][j];
    }



    public static void main(String[] args) {
        MinPalindromeCuts solver = new MinPalindromeCuts();
        String s = "aab";
        System.out.println("Minimum cuts: " + solver.minCut(s)); // Output: 1
    }
}

