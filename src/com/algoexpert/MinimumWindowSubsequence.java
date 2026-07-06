package com.algoexpert;

public class MinimumWindowSubsequence {

    public String minWindow(String s, String t) {
        int n = s.length(), m = t.length();
        int minLen = Integer.MAX_VALUE;
        int start = -1;

        int i = 0;
        while (i < n) {
            int j = 0;

            // 1. Forward scan to match t
            while (i < n) {
                if (s.charAt(i) == t.charAt(j)) {
                    j++;
                    if (j == m) break;
                }
                i++;
            }

            // If we didn't match all of t
            if (j < m) break;

            // 2. Backtrack to minimize window
            int end = i;
            j = m - 1;
            while (i >= 0) {
                if (s.charAt(i) == t.charAt(j)) {
                    j--;
                    if (j < 0) break;
                }
                i--;
            }

            i++; // correct start position

            // 3. Update answer
            if (end - i + 1 < minLen) {
                minLen = end - i + 1;
                start = i;
            }

            // 4. Move i forward to search for next window
            i = i + 1;
        }

        return start == -1 ? "" : s.substring(start, start + minLen);
    }

    static void main(String[] args) {
        MinimumWindowSubsequence solution = new MinimumWindowSubsequence();
        System.out.println(solution.minWindow("ABDC", "ABC"));
    }

}
