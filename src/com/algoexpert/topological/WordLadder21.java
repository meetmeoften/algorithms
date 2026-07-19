package com.algoexpert.topological;

import java.util.*;

public class WordLadder21 {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();

        if (!dict.contains(endWord))
            return result;

        Map<String, List<String>> parents = new HashMap<>();
        Map<String, Integer> level = new HashMap<>();

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        level.put(beginWord, 0);

        while (!queue.isEmpty()) {
            String word = queue.poll();
            int currLevel = level.get(word);

            char[] arr = word.toCharArray();

            for (int i = 0; i < arr.length; i++) {
                char original = arr[i];

                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == original) continue;

                    arr[i] = c;
                    String next = new String(arr);

                    if (!dict.contains(next)) continue;

                    if (!level.containsKey(next)) {
                        level.put(next, currLevel + 1);
                        queue.offer(next);
                        parents.put(next, new ArrayList<>());
                        parents.get(next).add(word);
                    } else if (level.get(next) == currLevel + 1) {
                        parents.get(next).add(word);
                    }
                }

                arr[i] = original;
            }
        }

        if (!level.containsKey(endWord))
            return result;

        List<String> path = new ArrayList<>();
        dfs(endWord, beginWord, parents, path, result);

        return result;
    }

    private void dfs(String word, String beginWord,
                     Map<String, List<String>> parents,
                     List<String> path,
                     List<List<String>> result) {

        path.add(word);

        if (word.equals(beginWord)) {
            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp);
            result.add(temp);
        } else {
            if (parents.containsKey(word)) {
                for (String parent : parents.get(word)) {
                    dfs(parent, beginWord, parents, path, result);
                }
            }
        }

        path.remove(path.size() - 1);
    }

    static void main(String[] args) {
        WordLadder21 wordLadder2 = new WordLadder21();
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        List<List<String>> result = wordLadder2.findLadders("hit", "cog", wordList);
        System.out.println(result);

    }
}
