package com.algoexpert.topological;

public class ShortestWordDistance {

    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int i1 = -1, i2 = -1;
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < wordsDict.length; i++) {
            if (wordsDict[i].equals(word1)) {
                i1 = i;
            }

            if (wordsDict[i].equals(word2)) {
                i2 = i;
            }

            if (i1 != -1 && i2 != -1) {
                minDist = Math.min(minDist, Math.abs(i1 - i2));
            }
        }

        return minDist;
    }

    public static void main(String[] args) {
        ShortestWordDistance shortestWordDistance = new ShortestWordDistance();
        shortestWordDistance.shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "makes", "coding");
    }
}
