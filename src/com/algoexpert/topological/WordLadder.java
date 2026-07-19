package com.algoexpert.topological;

import java.util.*;

public class WordLadder {


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordSet = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if(word.equals(endWord)){
                    return level;
                }
                char[] charArray = word.toCharArray();
                for (int j = 0; j < charArray.length; j++) {
                    char oldChar = charArray[j];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        charArray[j] = ch;
                        String newWord = new String(charArray);
                        if(wordSet.contains(newWord)){
                            queue.add(newWord);
                            wordSet.remove(newWord);
                        }
                    }
                    charArray[j] = oldChar;
                }
            }
            level++;
        }
        return 0;
    }


}
