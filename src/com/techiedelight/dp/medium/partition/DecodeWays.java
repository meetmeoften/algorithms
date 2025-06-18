package com.techiedelight.dp.medium.partition;

import java.util.HashMap;
import java.util.Map;

public class DecodeWays {

    public static void main(String[] args) {
        //String s = "226";
        String s = "11";
        System.out.println("Total decodings: " + numDecodings(s));
    }

    public static int numDecodings(String s) {
        Map<Integer, Integer> memo = new HashMap<>();
        return dfs(s, 0, memo);
    }

    private static int dfs(String s, int i, Map<Integer, Integer> memo) {
        if (i == s.length()) return 1; // reached the end = 1 valid decoding
        if (s.charAt(i) == '0') return 0; // invalid path (starts with 0)
        if (memo.containsKey(i)) return memo.get(i);

        int count = dfs(s, i + 1, memo); // single digit

        // two-digit decode
        if (i + 1 < s.length()) {
            int num = Integer.parseInt(s.substring(i, i + 2));
            if (num <= 26) {
                count += dfs(s, i + 2, memo);
            }
        }

        memo.put(i, count);
        return count;
    }
}

