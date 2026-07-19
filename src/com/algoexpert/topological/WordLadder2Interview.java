package com.algoexpert.topological;

import java.util.*;

public class WordLadder2Interview {

    public static List<List<String>> findLadders(String beginWord,
                                                 String endWord,
                                                 List<String> wordList) {

        List<List<String>> result = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);

        if (!dict.contains(endWord))
            return result;

        Map<String, List<String>> parents = new HashMap<>();
        Map<String, Integer> level = new HashMap<>();

        bfs(beginWord, endWord, dict, parents, level);

        if (!level.containsKey(endWord))
            return result;

        List<String> path = new ArrayList<>();
        dfs(endWord, beginWord, parents, path, result);

        return result;
    }

    private static void bfs(String beginWord,
                            String endWord,
                            Set<String> dict,
                            Map<String, List<String>> parents,
                            Map<String, Integer> level) {

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        level.put(beginWord, 0);

        while (!queue.isEmpty()) {

            String word = queue.poll();
            int currLevel = level.get(word);

            char[] arr = word.toCharArray();

            for (int i = 0; i < arr.length; i++) {

                char old = arr[i];

                for (char c = 'a'; c <= 'z'; c++) {

                    if (c == old)
                        continue;

                    arr[i] = c;
                    String next = new String(arr);

                    if (!dict.contains(next))
                        continue;

                    // First time visiting this word
                    if (!level.containsKey(next)) {
                        level.put(next, currLevel + 1);
                        queue.offer(next);
                        parents.putIfAbsent(next, new ArrayList<>());
                        parents.get(next).add(word);
                    }
                    // Another shortest path found
                    else if (level.get(next) == currLevel + 1) {
                        parents.get(next).add(word);
                    }
                }

                arr[i] = old;
            }
        }
    }

    private static void dfs(String word,
                            String beginWord,
                            Map<String, List<String>> parents,
                            List<String> path,
                            List<List<String>> result) {

        path.add(word);

        if (word.equals(beginWord)) {

            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp);
            result.add(temp);

        } else {

            for (String parent : parents.getOrDefault(word, Collections.emptyList())) {
                dfs(parent, beginWord, parents, path, result);
            }
        }

        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {

        String beginWord = "hit";
        String endWord = "cog";

        List<String> wordList = Arrays.asList(
                "hot", "dot", "dog", "lot", "log", "cog"
        );

        List<List<String>> ans = findLadders(beginWord, endWord, wordList);

        for (List<String> path : ans) {
            System.out.println(path);
        }
    }
}
