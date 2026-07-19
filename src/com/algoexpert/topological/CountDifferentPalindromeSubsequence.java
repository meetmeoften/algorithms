package com.algoexpert.topological;

public class CountDifferentPalindromeSubsequence {

    private static final int MOD = 1_000_000_007;

    private String s;
    private Long[][] memo;

    public int countPalindromicSubsequences(String s) {
        this.s = s;
        int n = s.length();
        memo = new Long[n][n];
        return (int) dfs(0, n - 1);
    }

    private long dfs(int i, int j) {
        if (i > j) return 0;
        if (i == j) return 1;
        if (memo[i][j] != null)
            return memo[i][j];
        long ans;
        if (s.charAt(i) != s.charAt(j)) {
            ans = dfs(i + 1, j)
                    + dfs(i, j - 1)
                    - dfs(i + 1, j - 1);
        } else {
            int left = i + 1;
            int right = j - 1;
            while (left <= right && s.charAt(left) != s.charAt(i))
                left++;
            while (left <= right && s.charAt(right) != s.charAt(i))
                right--;

            if (left > right) {
                ans = 2 * dfs(i + 1, j - 1) + 2;
            } else if (left == right) {
                ans = 2 * dfs(i + 1, j - 1) + 1;
            } else {
                ans = 2 * dfs(i + 1, j - 1)
                        - dfs(left + 1, right - 1);
            }
        }
        ans %= MOD;
        if (ans < 0) ans += MOD;
        return memo[i][j] = ans;
    }

    public static void main(String[] args) {
        CountDifferentPalindromeSubsequence sol = new CountDifferentPalindromeSubsequence();

        String s = "bccb";
        System.out.println(sol.countPalindromicSubsequences(s)); // 6

        s = "abcd";
        System.out.println(sol.countPalindromicSubsequences(s)); // 4

        s = "aaa";
        System.out.println(sol.countPalindromicSubsequences(s)); // 3
    }
}
