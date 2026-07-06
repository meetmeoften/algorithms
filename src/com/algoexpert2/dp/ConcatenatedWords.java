package com.algoexpert2.dp;

import java.util.*;

public class ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        Set<String> dict = new HashSet<>();
        List<String> result = new ArrayList<>();
        for(String word: words) {
            if(word.length() == 0) continue;
            if(canForm(word, dict, 0)) {
                result.add(word);
            }
            dict.add(word);
        }
        return result;
    }


    private boolean canForm(String word, Set<String> dict, int start) {
        if(start == word.length()) return true;
        for(int end = start + 1; end <= word.length(); end++) {
            String curr = word.substring(start, end);
            if(dict.contains(curr)) {
                if(canForm(word, dict, end)) {
                    return true;
                }
            }
        }
        return false;
    }

    static void main(String[] args) {
        ConcatenatedWords concat = new ConcatenatedWords();
        concat.findAllConcatenatedWordsInADict(new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"});
    }
}
