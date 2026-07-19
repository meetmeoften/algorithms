package com.algoexpert.topological;

import java.util.*;

public class WordLadder2 {


    public List<List<String>> findLadders(String beginWord,
                                          String endWord,
                                          List<String> wordList) {

        List<List<String>> result = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);

        if (!dict.contains(endWord))
            return result;


        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        boolean found = false;
        Map<String, List<String>> parents = new HashMap<>();

        while (!queue.isEmpty() && !found) {

            int size = queue.size();

            Set<String> levelVisited = new HashSet<>();

            for (int i = 0; i < size; i++) {

                String word = queue.poll();

                char[] arr = word.toCharArray();

                for (int j = 0; j < arr.length; j++) {

                    char original = arr[j];

                    for (char c = 'a'; c <= 'z'; c++) {

                        arr[j] = c;

                        String next = new String(arr);

                        if (!dict.contains(next))
                            continue;

                        if (!visited.contains(next)) {

                            if (!levelVisited.contains(next)) {
                                queue.offer(next);
                                levelVisited.add(next);
                            }

                            parents
                                    .computeIfAbsent(next, k -> new ArrayList<>())
                                    .add(word);

                            if (next.equals(endWord))
                                found = true;
                        }
                    }

                    arr[j] = original;
                }
            }

            visited.addAll(levelVisited);
        }

        if (!found)
            return result;

        LinkedList<String> path = new LinkedList<>();

        dfs(endWord, beginWord, parents, path, result);

        return result;
    }

    private void dfs(String word,
                     String begin,
                     Map<String, List<String>> parents,
                     LinkedList<String> path,
                     List<List<String>> result) {

        path.addFirst(word);

        if (word.equals(begin)) {
            result.add(new ArrayList<>(path));
        } else {

            List<String> prev = parents.get(word);

            if (prev != null) {
                for (String p : prev) {
                    dfs(p, begin, parents, path, result);
                }
            }
        }

        path.removeFirst();
    }

    static void main(String[] args) {
        WordLadder2 wordLadder2 = new WordLadder2();
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

    
