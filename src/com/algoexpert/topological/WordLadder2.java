package com.algoexpert.topological;

import java.util.*;

public class WordLadder2 {

    public static List<List<String>> findLadders(String beginWord,
                                                 String endWord,
                                                 List<String> wordList) {

        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();

        if (!dict.contains(endWord))
            return result;

        Map<String, List<String>> graph = new HashMap<>(); // parents
        Map<String, Integer> distance = new HashMap<>();

        bfs(beginWord, endWord, dict, graph, distance);

        List<String> path = new ArrayList<>();
        path.add(beginWord);

        dfs(beginWord, endWord, graph, distance, path, result);

        return result;
    }

    private static void bfs(String beginWord,
                            String endWord,
                            Set<String> dict,
                            Map<String, List<String>> graph,
                            Map<String, Integer> distance) {

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);

        for (String word : dict)
            graph.put(word, new ArrayList<>());

        graph.put(beginWord, new ArrayList<>());

        while (!queue.isEmpty()) {

            int size = queue.size();
            boolean found = false;

            for (int i = 0; i < size; i++) {

                String word = queue.poll();
                int currDist = distance.get(word);

                for (String next : getNeighbors(word, dict)) {

                    graph.get(word).add(next);

                    if (!distance.containsKey(next)) {
                        distance.put(next, currDist + 1);

                        if (next.equals(endWord))
                            found = true;
                        else
                            queue.offer(next);
                    }
                }
            }

            if (found)
                break;
        }
    }

    private static void dfs(String curr,
                            String end,
                            Map<String, List<String>> graph,
                            Map<String, Integer> distance,
                            List<String> path,
                            List<List<String>> result) {

        if (curr.equals(end)) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (String next : graph.getOrDefault(curr, new ArrayList<>())) {

            if (distance.get(next) == distance.get(curr) + 1) {

                path.add(next);

                dfs(next, end, graph, distance, path, result);

                path.remove(path.size() - 1);
            }
        }
    }

    private static List<String> getNeighbors(String word, Set<String> dict) {

        List<String> neighbors = new ArrayList<>();

        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            char old = chars[i];

            for (char c = 'a'; c <= 'z'; c++) {

                if (c == old)
                    continue;

                chars[i] = c;

                String newWord = new String(chars);

                if (dict.contains(newWord))
                    neighbors.add(newWord);
            }

            chars[i] = old;
        }

        return neighbors;
    }

    public static void main(String[] args) {

        String beginWord = "hit";
        String endWord = "cog";

        List<String> wordList = Arrays.asList(
                "hot", "dot", "dog", "lot", "log", "cog");

        List<List<String>> ans =
                findLadders(beginWord, endWord, wordList);

        for (List<String> path : ans) {
            System.out.println(path);
        }
    }
}
