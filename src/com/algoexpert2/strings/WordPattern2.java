package com.algoexpert2.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPattern2 {

    public boolean wordPatternMatch(String pattern, String s) {
        Map<Character, String> resultMap = new HashMap<>();
        boolean matched = backtrack(pattern, 0, s, 0, resultMap, new HashSet<>());
        if (matched) {
            System.out.println("Pattern mapping:");
            for (char c : resultMap.keySet()) {
                System.out.println(c + " → " + resultMap.get(c));
            }
        }
        return matched;
    }


    private boolean backtrack(String pattern, int pIndex, String s, int sIndex,
                              Map<Character, String> map, Set<String> used) {

        // If we reached both pattern and string end → success
        if (pIndex == pattern.length() && sIndex == s.length()) return true;

        // If only one finished → mismatch
        if (pIndex == pattern.length() || sIndex == s.length()) return false;

        char currentChar = pattern.charAt(pIndex);

        // If pattern letter already mapped
        if (map.containsKey(currentChar)) {
            String target = map.get(currentChar);

            // If the next characters in s do NOT match this mapping → fail
            if (!s.startsWith(target, sIndex)) return false;

            // Continue with next part
            return backtrack(pattern, pIndex + 1,
                    s, sIndex + target.length(),
                    map, used);
        }

        // Try all possible substrings for a new character mapping
        for (int end = sIndex + 1; end <= s.length(); end++) {
            String candidate = s.substring(sIndex, end);

            // Skip if some other pattern letter already uses this substring
            //if (used.contains(candidate)) continue;

            // Try mapping currentChar → candidate
            map.put(currentChar, candidate);
            //used.add(candidate);

            if (backtrack(pattern, pIndex + 1, s, end, map, used)) {
                return true;
            }

            // Backtrack
            map.remove(currentChar);
            used.remove(candidate);
        }

        // No valid mapping found
        return false;
    }

    // Quick test
    public static void main(String[] args) {
        WordPattern2 solver = new WordPattern2();
        System.out.println(solver.wordPatternMatch("aba", "reblre")); // true
        System.out.println(solver.wordPatternMatch("aaaa", "asdasdasdasd"));   // true
        System.out.println(solver.wordPatternMatch("aabb", "xyzabcxzyabc"));   // false
    }
}

