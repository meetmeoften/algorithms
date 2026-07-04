package com.algoexpert.arrays.leetcode300;

public class RepeatedSubstring {


    public boolean repeatedSubstringPattern(String s) {
        String doubled = s + s;
        String trimmed = doubled.substring(1, doubled.length() - 1);

        int index = trimmed.indexOf(s);
        if (index == -1) return false;

        String res = s.substring(0, index + 1);
        System.out.println(res);

        return true;
    }

        public String repeatedSubstringPattern1(String s) {
            int n = s.length();
            for (int len = 1; len <= n / 2; len++) {
                if (n % len == 0) {
                    String sub = s.substring(0, len);
                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < n / len; i++) {
                        sb.append(sub);
                    }

                    if (sb.toString().equals(s)) {
                        return sub; // ✅ return the repeating substring
                    }
                }
            }

            return ""; // ❌ no repeated substring
        }


    public static void main(String[] args) {

        RepeatedSubstring repeatedSubstring = new RepeatedSubstring();
        repeatedSubstring.repeatedSubstringPattern1("abab");
    }
}
