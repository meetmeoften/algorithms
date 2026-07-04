package com.algoexpert.topological;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ShortestWordDistance2 {

    private Map<String, List<Integer>> map;

    public ShortestWordDistance2(String[] wordsDict) {
        map = new HashMap<>();

        for (int i = 0; i < wordsDict.length; i++) {
            map.computeIfAbsent(wordsDict[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);

        int i = 0, j = 0;
        int minDist = Integer.MAX_VALUE;

        while (i < list1.size() && j < list2.size()) {
            int idx1 = list1.get(i);
            int idx2 = list2.get(j);

            minDist = Math.min(minDist, Math.abs(idx1 - idx2));

            if (idx1 < idx2) {
                i++;
            } else {
                j++;
            }
        }

        return minDist;
    }

    public static void main(String[] args) {

        String[] wordsDict = {
                "practice", "makes", "perfect", "coding", "makes"
        };

        ShortestWordDistance2 wd = new ShortestWordDistance2(wordsDict);

        System.out.println(wd.shortest("coding", "practice")); // 3
        System.out.println(wd.shortest("makes", "coding"));    // 1
    }
}
