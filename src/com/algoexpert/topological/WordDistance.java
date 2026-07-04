package com.algoexpert.topological;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordDistance {

    private Map<String, List<Integer>> map;

    public WordDistance(String[] wordsDict) {
        map = new HashMap<>();

        for (int i = 0; i < wordsDict.length; i++) {
            String word = wordsDict[i];

            map.computeIfAbsent(word, key -> new ArrayList<>()).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);

        // SAME WORD CASE
        if (word1.equals(word2)) {
            int min = Integer.MAX_VALUE;
            for (int i = 1; i < list1.size(); i++) {
                min = Math.min(min, list1.get(i) - list1.get(i - 1));
            }
            return min;
        }

        int i = 0;
        int j = 0;
        int minDistance = Integer.MAX_VALUE;

        while (i < list1.size() && j < list2.size()) {
            int index1 = list1.get(i);
            int index2 = list2.get(j);

            minDistance = Math.min(minDistance, Math.abs(index1 - index2));

            if (index1 < index2) {
                i++;
            } else {
                j++;
            }
        }

        return minDistance;
    }


    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int minDistance = Integer.MAX_VALUE;

        if (word1.equals(word2)) {
            int prevIndex = -1;

            for (int i = 0; i < wordsDict.length; i++) {
                if (wordsDict[i].equals(word1)) {
                    if (prevIndex != -1) {
                        minDistance = Math.min(minDistance, i - prevIndex);
                    }
                    prevIndex = i;
                }
            }

            return minDistance;
        }

        int index1 = -1;
        int index2 = -1;

        for (int i = 0; i < wordsDict.length; i++) {
            if (wordsDict[i].equals(word1)) {
                index1 = i;
            } else if (wordsDict[i].equals(word2)) {
                index2 = i;
            }

            if (index1 != -1 && index2 != -1) {
                minDistance = Math.min(minDistance, Math.abs(index1 - index2));
            }
        }

        return minDistance;
    }

    public static void main(String[] args) {
        String[] words = {
                "coding", "makes", "perfect", "practice", "makes"
        };

        ShortestWordDistance2 wd = new ShortestWordDistance2(words);

        System.out.println(wd.shortest("coding", "practice"));
        // Output: 3

        System.out.println(wd.shortest("coding", "makes"));
        // Output: 1
    }

}
