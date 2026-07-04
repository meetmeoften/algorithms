package com.algoexpert.topological;

public class ShortestWordDistance3 {


    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {

        int i1 = -1, i2 = -1;
        int minDist = Integer.MAX_VALUE;

        // Case when both words are same
        boolean same = word1.equals(word2);

        for (int i = 0; i < wordsDict.length; i++) {

            if (wordsDict[i].equals(word1)) {

                if (same) {
                    // treat previous occurrence as i1
                    if (i1 != -1) {
                        minDist = Math.min(minDist, i - i1);
                    }
                    i1 = i;
                } else {
                    i1 = i;
                    if (i2 != -1) {
                        minDist = Math.min(minDist, Math.abs(i1 - i2));
                    }
                }

            } else if (!same && wordsDict[i].equals(word2)) {
                i2 = i;
                if (i1 != -1) {
                    minDist = Math.min(minDist, Math.abs(i1 - i2));
                }
            }
        }

        return minDist;
    }

    // Main method
    public static void main(String[] args) {

        ShortestWordDistance3 sol = new ShortestWordDistance3();

        String[] wordsDict = {
                "practice", "makes", "perfect", "coding", "makes"
        };

        System.out.println(sol.shortestWordDistance(wordsDict, "makes", "coding")); // 1
        System.out.println(sol.shortestWordDistance(wordsDict, "makes", "makes"));   // 3
    }
}
