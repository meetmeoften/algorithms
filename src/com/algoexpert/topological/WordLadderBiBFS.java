package com.algoexpert.topological;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadderBiBFS {

    public static int ladderLength(String beginWord,
                                   String endWord,
                                   List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord))
            return 0;
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        int level = 1;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // Always expand the smaller side
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }
            Set<String> nextLevel = new HashSet<>();
            for (String word : beginSet) {
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char original = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original)
                            continue;
                        chars[i] = c;
                        String next = new String(chars);
                        // Other BFS side reached it
                        if (endSet.contains(next)) {
                            return level + 1;
                        }
                        if (dict.contains(next)) {
                            nextLevel.add(next);
                            dict.remove(next);
                        }
                    }
                    chars[i] = original;
                }
            }
            beginSet = nextLevel;
            level++;
        }
        return 0;
    }


    public static void main(String[] args) {


        String beginWord = "hit";
        String endWord = "cog";

        List<String> wordList = Arrays.asList(
                "hot",
                "dot",
                "dog",
                "lot",
                "log",
                "cog"
        );

        System.out.println(
                ladderLength(
                        beginWord,
                        endWord,
                        wordList
                )
        );
    }
}
