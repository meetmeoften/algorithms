package com.google.graph;

import java.util.*;

public class FindAllPeopleWithSecret {

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // People who know the secret
        Set<Integer> secret = new HashSet<>();
        secret.add(0);
        secret.add(firstPerson);

        // Sort meetings by time
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);

        int i = 0;
        while (i < meetings.length) {
            int time = meetings[i][2];

            // Graph for current time
            Map<Integer, List<Integer>> graph = new HashMap<>();
            Set<Integer> peopleInTime = new HashSet<>();

            // Collect meetings at same time
            while (i < meetings.length && meetings[i][2] == time) {
                int u = meetings[i][0];
                int v = meetings[i][1];
                graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
                graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
                peopleInTime.add(u);
                peopleInTime.add(v);
                i++;
            }

            // DFS only from people who already know the secret
            Set<Integer> visited = new HashSet<>();
            for (int p : peopleInTime) {
                if (secret.contains(p) && !visited.contains(p)) {
                    dfs(p, graph, visited);
                }
            }
            // Everyone visited now knows the secret
            secret.addAll(visited);
        }
        return new ArrayList<>(secret);
    }

    private void dfs(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        visited.add(node);
        if (!graph.containsKey(node)) return;

        for (int nei : graph.get(node)) {
            if (!visited.contains(nei)) {
                dfs(nei, graph, visited);
            }
        }
    }

    public static void main(String[] args) {
        FindAllPeopleWithSecret obj = new FindAllPeopleWithSecret();
        int[][] meetings = {{1, 2, 5}, {2, 3, 8}, {1, 5, 10}};
        int firstPerson =1;
        System.out.println(obj.findAllPeople(6, meetings, firstPerson));
    }
}
