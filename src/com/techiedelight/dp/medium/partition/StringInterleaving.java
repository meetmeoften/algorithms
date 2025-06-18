package com.techiedelight.dp.medium.partition;

import java.util.HashMap;
import java.util.Map;

public class StringInterleaving {


    public static void main(String[] args) {
        String A = "abc";
        String B = "def";
        String C = "abdecf";
        //String C = "adebcf";
        System.out.println("Is Interleaving: " + isInterleave(A, B, C));
    }

    public static boolean isInterleave(String A, String B, String C) {
        if (A.length() + B.length() != C.length()) return false;

        Map<String, Boolean> memo = new HashMap<>();
        return dfs(A, 0, B, 0, C, 0, memo);
    }

    private static boolean dfs(String A, int i, String B, int j, String C, int k, Map<String, Boolean> memo) {
        if (k == C.length()) return i == A.length() && j == B.length();
        String key = i + "," + j;
        if (memo.containsKey(key)) return memo.get(key);

        boolean valid = false;
        if (i < A.length() && A.charAt(i) == C.charAt(k)) {
            valid |= dfs(A, i+1, B, j, C, k+1, memo);
        }

        if (j < B.length() && B.charAt(j) == C.charAt(k)) {
            valid |= dfs(A, i, B, j + 1, C, k + 1, memo);
        }
        memo.put(key, valid);
        return valid;
    }
}
