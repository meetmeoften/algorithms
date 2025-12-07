package com.algoexpert2.graph;

import java.util.*;

public class AirportConnections {

    public static int airportConnections(
            List<String> airports,
            List<List<String>> routes,
            String startingAirport) {

        // Build graph
        Map<String, List<String>> graph = new HashMap<>();
        for (String airport : airports) {
            graph.put(airport, new ArrayList<>());
        }
        for (List<String> route : routes) {
            graph.get(route.get(0)).add(route.get(1));
        }

        // Step 1: Find all reachable airports from startingAirport
        Set<String> reachable = new HashSet<>();
        dfs(startingAirport, graph, reachable);

        // Step 2: Build unreachable airports list
        List<String> unreachableAirports = new ArrayList<>();
        for (String airport : airports) {
            if (!reachable.contains(airport)) {
                unreachableAirports.add(airport);
            }
        }

        // Step 3: For each unreachable airport, compute its reachable set (its "component")
        Map<String, Set<String>> unreachableComponents = new HashMap<>();
        for (String airport : unreachableAirports) {
            Set<String> visited = new HashSet<>();
            dfs(airport, graph, visited);
            // Only keep airports not reachable from starting point
            visited.removeAll(reachable);
            unreachableComponents.put(airport, visited);
        }

        // Step 4: Sort unreachable airports by size of reachable component (descending)
        unreachableAirports.sort((a, b) ->
                unreachableComponents.get(b).size() - unreachableComponents.get(a).size());

        // Step 5: Add minimum connections
        int addedRoutes = 0;
        for (String airport : unreachableAirports) {
            if (reachable.contains(airport)) continue; // might be reachable by now

            addedRoutes++;
            // Mark all its component nodes as reachable now
            reachable.addAll(unreachableComponents.get(airport));
        }

        return addedRoutes;
    }

    private static void dfs(String airport,
                            Map<String, List<String>> graph,
                            Set<String> visited) {
        if (visited.contains(airport)) return;
        visited.add(airport);
        for (String next : graph.get(airport)) {
            dfs(next, graph, visited);
        }
    }

    public static void main(String[] args) {
        List<String> airports = Arrays.asList("A", "B", "C", "D", "E");
        List<List<String>> routes = Arrays.asList(
                Arrays.asList("A", "B"),
                Arrays.asList("B", "C"),
                Arrays.asList("C", "A"),
                Arrays.asList("D", "E")
        );

        System.out.println(
                airportConnections(airports, routes, "A")
        );  // Expected: 1
    }
}

