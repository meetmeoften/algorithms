package com.techiedelight.dp.medium.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumFlightCost {

    static class City {
        int id;
        int cost;

        public City(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }
    }

    public static int dijkstra(int N, int[][] cost) {
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<City> pq = new PriorityQueue<>(Comparator.comparingInt(c -> c.cost));
        pq.add(new City(0, 0)); // Step 2: Process cities using Dijkstra's algorithm
        while (!pq.isEmpty()) {
            City current = pq.poll();
            int currentCity = current.id;
            int currentCost = current.cost;

            // If we reach the destination city N-1, return the cost
            if (currentCity == N - 1) {
                return currentCost;
            }

            // Explore all neighboring cities (cities directly reachable from the current city)
            for (int nextCity = 0; nextCity < N; nextCity++) {
                if (cost[currentCity][nextCity] > 0) { // A direct flight exists
                    int newCost = currentCost + cost[currentCity][nextCity];
                    // If we found a cheaper path to nextCity, update the cost and add to priority queue
                    if (newCost < dist[nextCity]) {
                        dist[nextCity] = newCost;
                        pq.add(new City(nextCity, newCost));
                    }
                }
            }
        }
        // If we cannot reach the destination city, return -1 (or an appropriate value)
        return -1;
    }

    public static int minCostDP(int N, int[][] cost) {
        // Step 1: Initialize the DP array with "infinity" (a large number)
        int[] dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // Cost to reach the source city (city 0) is 0

        // Step 2: DP iteration to calculate the minimum cost to reach each city
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cost[i][j] > 0) { // There is a direct flight from city i to city j
                    dp[j] = Math.min(dp[j], dp[i] + cost[i][j]);
                }
            }
        }

        // Step 3: Return the minimum cost to reach city N-1
        return dp[N - 1] == Integer.MAX_VALUE ? -1 : dp[N - 1]; // If city N-1 is unreachable, return -1
    }

    public static void main(String[] args) {
        // Example input: Cost matrix for 4 cities
        int[][] cost = {
                {0, 20, 30, 100},  // city 0 to city 1, 2, 3
                {20, 0, 15, 75},   // city 1 to city 0, 2, 3
                {30, 15, 0, 50},   // city 2 to city 0, 1, 3
                {100, 75, 50, 0}   // city 3 to city 0, 1, 2
        };

        int N = cost.length; // Number of cities
        int minCost = minCostDP(N, cost);

        System.out.println("The minimum cost to reach the destination city is: " + minCost);
    }
}
