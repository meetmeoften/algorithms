package com.techiedelight.dp.hard.partition;

import java.util.*;

public class WordBreakSolver {

    private Set<String> wordSet;
    private Map<Integer, Boolean> memo;

    private Map<Integer, List<String>> memo2;

    public boolean wordBreak(String s, List<String> wordDict) {
        wordSet = new HashSet<>(wordDict);
        memo = new HashMap<>();
        return canBreak(s, 0);
    }

    public List<String> wordBreak2(String s, List<String> wordDict) {
        wordSet = new HashSet<>(wordDict);
        memo2 = new HashMap<>();
        return dfs(s, 0);
    }

    private boolean canBreak(String s, int start) {
        if (start == s.length()) return true;
        if (memo.containsKey(start)) return memo.get(start);

        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (wordSet.contains(word) && canBreak(s, end)) {
                memo.put(start, true);
                return true;
            }
        }

        memo.put(start, false);
        return false;
    }

    private List<String> dfs (String s, int start) {
        List<String> result = new ArrayList<>();
        if(start == s.length()) {
            result.add("");
            return result;
        }

        if(memo2.containsKey(start)) return memo2.get(start);

        for(int end = start+1; end <=s.length(); end++) {
            String word = s.substring(start, end);
            if(wordSet.contains(word)) {
                List<String> list = dfs(s, end);
                for(String val : list) {
                    String space = val.isEmpty() ? "" : " ";
                    result.add(word + space + val);
                }
            }
        }
        memo2.put(start, result);
        return result;
    }

    // Example usage
    public static void main(String[] args) {
        WordBreakSolver solver = new WordBreakSolver();
        String s = "applepensapple";
        List<String> dict = Arrays.asList("apple", "pen");
        System.out.println("Can break? " + solver.wordBreak(s, dict)); // true

        System.out.println("Can break? " + solver.wordBreak2(s, dict)); // true
    }
}
