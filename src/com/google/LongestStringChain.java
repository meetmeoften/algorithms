package com.google;

import java.util.*;

public class LongestStringChain {

    public static int longestStrChain(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int res = 0;
        for (String word : words) {
            int best = 0;
            for (int i = 0; i < word.length(); i++) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                best = Math.max(best, map.getOrDefault(prev, 0) + 1);
            }
            map.put(word, best);
            res = Math.max(res, best);
        }
        return res;
    }

    public static int longestStrChain2(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        Map<String, Integer> dp = new HashMap<>();    // chain length
        Map<String, String> parent = new HashMap<>(); // predecessor for reconstruction

        int longest = 1;
        String lastWord = words[0]; // will store last word of the longest chain

        for (String word : words) {
            int best = 1;
            String bestPred = null;

            for (int i = 0; i < word.length(); i++) {
                String pred = word.substring(0, i) + word.substring(i + 1);

                if (dp.containsKey(pred) && dp.get(pred) + 1 > best) {
                    best = dp.get(pred) + 1;
                    bestPred = pred;
                }
            }

            dp.put(word, best);
            parent.put(word, bestPred);

            if (best > longest) {
                longest = best;
                lastWord = word;
            }
        }

        // 🔥 Reconstruct and print the chain
        List<String> chain = new ArrayList<>();
        while (lastWord != null) {
            chain.add(lastWord);
            lastWord = parent.get(lastWord);
        }

        Collections.reverse(chain);
        System.out.println("Longest Chain: " + chain);

        return longest;
    }


    public static void main(String[] args) {
        String[] words = {"a", "b", "ba", "bca", "bda", "bdca"};
        longestStrChain2(words);
    }
}
