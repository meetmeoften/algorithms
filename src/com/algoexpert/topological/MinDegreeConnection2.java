package com.algoexpert.topological;

import java.util.*;

public class MinDegreeConnection2 {

    public List<String> findPath(Map<String, List<String>> graph, String S, String P) {
        if (S.equals(P)) return Arrays.asList(S);

        Queue<String> qS = new LinkedList<>();
        Queue<String> qP = new LinkedList<>();

        Map<String, String> parentS = new HashMap<>();
        Map<String, String> parentP = new HashMap<>();

        qS.offer(S);
        qP.offer(P);

        parentS.put(S, null);
        parentP.put(P, null);

        while (!qS.isEmpty() && !qP.isEmpty()) {
            String meet = expand(graph, qS, parentS, parentP);
            if (meet != null) {
                return buildPath(meet, parentS, parentP);
            }

            meet = expand(graph, qP, parentP, parentS);
            if (meet != null) {
                return buildPath(meet, parentS, parentP);
            }
        }
        return new ArrayList<>();
    }

    private String expand(
            Map<String, List<String>> graph,
            Queue<String> queue,
            Map<String, String> parentThisSide,
            Map<String, String> parentOtherSide) {
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            String curr = queue.poll();

            for (String nei : graph.getOrDefault(curr, new ArrayList<>())) {
                if (parentThisSide.containsKey(nei)) continue;
                parentThisSide.put(nei, curr);
                if (parentOtherSide.containsKey(nei)) {
                    return nei;
                }
                queue.offer(nei);
            }
        }
        return null;
    }

    private List<String> buildPath(
            String meet,
            Map<String, String> parentS,
            Map<String, String> parentP) {
        LinkedList<String> path = new LinkedList<>();
        String curr = meet;
        while (curr != null) {
            path.addFirst(curr);
            curr = parentS.get(curr);
        }
        curr = parentP.get(meet);
        while (curr != null) {
            path.addLast(curr);
            curr = parentP.get(curr);
        }

        return path;
    }
}