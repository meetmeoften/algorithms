package com.algoexpert2;

import java.util.*;

public class Subsequence {

    static class Node {
        String word;
        int index;

        Node(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }

    public static int numMatchingSubseq2(String s, String[] words) {
        Map<Character, List<Node>> map = new HashMap<>();
        // Initialize map
        for (String word : words) {
            char firstChar = word.charAt(0);
            map.computeIfAbsent(firstChar, k -> new ArrayList<>()).add(new Node(word, 0));
        }
        int count = 0;

        // Process s
        for (char c : s.toCharArray()) {
            List<Node> waiting = map.get(c);
            if (waiting == null) continue;

            map.put(c, new ArrayList<>()); // clear current bucket

            for (Node node : waiting) {
                node.index++;

                if (node.index == node.word.length()) {
                    count++;
                } else {
                    char nextChar = node.word.charAt(node.index);
                    map.computeIfAbsent(nextChar, k -> new ArrayList<>())
                            .add(node);
                }
            }
        }
        return count;
    }

    public static int numMatchingSubseq(String s, String[] words) {
        Map<Character, Queue<String>> map = new HashMap<>();

        // Initialize buckets
        for (String word : words) {
            char firstChar = word.charAt(0);
            map.computeIfAbsent(firstChar, k -> new LinkedList<>())
                    .offer(word);
        }

        int count = 0;

        // Process string s
        for (char c : s.toCharArray()) {
            Queue<String> queue = map.get(c);
            if (queue == null) continue;

            int size = queue.size(); // important!
            for (int i = 0; i < size; i++) {
                String word = queue.poll();

                if (word.length() == 1) {
                    count++; // fully matched
                } else {
                    String rest = word.substring(1);
                    char nextChar = rest.charAt(0);
                    map.computeIfAbsent(nextChar, k -> new LinkedList<>())
                            .offer(rest);
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String s = "abcde";
        String[] words = {"a", "bb", "acd", "ace"};

        System.out.println(numMatchingSubseq(s, words)); // 3
    }
}
