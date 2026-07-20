package com.algoexpert.topological;

import java.util.*;

public class MinDegreeConnection {
    public static int minDegree(Map<String, List<String>> graph, String S, String P) {
        if(S.equals(P)) return 0;
        Set<String> visitedS = new HashSet<>();
        Set<String> visitedP = new HashSet<>();
        Queue<String> qS = new LinkedList<>();
        Queue<String> qP = new LinkedList<>();
        qS.offer(S);
        qP.offer(P);
        visitedS.add(S);
        visitedS.add(P);

        int degree = 0;
        while(!qS.isEmpty() && !qP.isEmpty()) {
            degree++;
            if(qS.size() > qP.size()) {
                Queue<String> tempQ = qS;
                qS = qP;
                qP = tempQ;
                Set<String> tempV = visitedS;
                visitedS = visitedP;
                visitedP = tempV;
            }
            int size = qS.size();
            for(int i= 0; i < size; i++) {
                String curr = qS.poll();
                for(String nei: graph.getOrDefault(curr, new ArrayList<>())) {
                    if(visitedS.contains(nei)) {
                        continue;
                    }
                    if(visitedP.contains(nei)) {
                        return degree;
                    }
                    visitedS.add(nei);
                    qS.offer(nei);
                }
            }
        }
        return -1;
    }

    public int connectionDistance(Map<String, List<String>> graph,
                                  String start,
                                  String end) {

        if (start.equals(end)) {
            return 0;
        }
        Set<String> visited = new HashSet<>();

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        beginSet.add(start);
        endSet.add(end);

        visited.add(start);
        visited.add(end);

        int level = 0;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {

            // Always expand smaller side
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> nextLevel = new HashSet<>();

            for (String person : beginSet) {

                for (String neighbor : graph.getOrDefault(person,
                        Collections.emptyList())) {

                    // Other side already reached this person
                    if (endSet.contains(neighbor)) {
                        return level + 1;
                    }

                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        nextLevel.add(neighbor);
                    }
                }
            }

            beginSet = nextLevel;
            level++;
        }

        return -1;
    }

    public int shortestDistance(Map<String, List<String>> graph, String start, String end) {
        if(start.equals(end)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        int distance =0;
        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i= 0; i < size; i++) {
                String curr = queue.poll();
                if(curr.equals(end)) {
                    return distance;
                }
                for(String neighbour: graph.getOrDefault(curr, Collections.emptyList())) {
                    if(!visited.contains(neighbour)) {
                        visited.add(neighbour);
                        queue.add(neighbour);
                    }
                }
                distance++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        Map<String, List<String>> graph = new HashMap<>();

        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("A", "D"));
        graph.put("C", Arrays.asList("A", "D"));
        graph.put("D", Arrays.asList("B", "C", "E"));
        graph.put("E", Arrays.asList("D"));

        String S = "A";
        String P = "E";
        int ans = minDegree(graph, S, P);

        System.out.println("Minimum Degree of Connection = " + ans);
    }

}
