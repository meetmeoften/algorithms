package com.algoexpert.topological;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortWordDistance4 {

    private Map<String, List<Integer>> map = new HashMap<>();

    public ShortWordDistance4(String[] wordsDict) {
        for (int i = 0; i < wordsDict.length; i++) {
            map.computeIfAbsent(wordsDict[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        // Same word
        if (word1.equals(word2)) {
            int min = Integer.MAX_VALUE;
            for (int i = 1; i < list1.size(); i++) {
                min = Math.min(min, list1.get(i) - list1.get(i - 1));
            }
            return min;
        }

        // Different words
        List<Integer> list2 = map.get(word2);
        int i = 0, j = 0;
        int min = Integer.MAX_VALUE;
        while (i < list1.size() && j < list2.size()) {
            min = Math.min(min, Math.abs(list1.get(i) - list2.get(j)));
            if (list1.get(i) < list2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return min;
    }
}
