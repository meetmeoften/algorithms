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
