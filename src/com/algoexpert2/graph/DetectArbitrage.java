package com.algoexpert2.graph;

import java.util.*;

public class DetectArbitrage {

    /**
     * You are given a list of currencies and a 2D matrix exchangeRates, where
     * exchangeRates[i][j] represents how many units of currency j you get for 1 unit of currency i.
     * <p>
     * For example:
     * <p>
     * currencies = ["USD", "JPY", "EUR"]
     * exchangeRates = [
     * [1.0, 110.0, 0.9],   // USD -> USD, JPY, EUR
     * [0.0091, 1.0, 0.008],// JPY -> USD, JPY, EUR
     * [1.1, 130.0, 1.0]    // EUR -> USD, JPY, EUR
     * ]
     * <p>
     * <p>
     * Arbitrage exists if starting with 1 unit of some currency, you can convert through a series of exchanges and end up with more than 1 unit of the same currency.
     * <p>
     * Return true if arbitrage exists, otherwise false.
     *
     * @param exchangeRates
     * @return
     */

    public static boolean detectArbitrage(List<List<Double>> exchangeRates) {
        int n = exchangeRates.size();
        // Step 1: Build graph with -log(edge weights)
        double[][] graph = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = -Math.log(exchangeRates.get(i).get(j));
            }
        }
        // Step 2: Run Bellman-Ford from an arbitrary start (0)
        double[] dist = new double[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[0] = 0;

        // Relax edges V-1 times
        for (int iter = 0; iter < n - 1; iter++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    double newDist = dist[u] + graph[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                    }
                }
            }
        }
        // Step 3: Check for a negative cycle
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (dist[u] + graph[u][v] < dist[v]) {
                    return true; // Negative cycle → arbitrage exists
                }
            }
        }
        return false;
    }


    public static List<Integer> findArbitrageCycle(List<List<Double>> exchangeRates) {
        int n = exchangeRates.size();

        // Step 1: Convert exchange rates to -log graph
        double[][] graph = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = -Math.log(exchangeRates.get(i).get(j));
            }
        }
        // Step 2: Bellman-Ford setup
        double[] dist = new double[n];
        int[] parent = new int[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        Arrays.fill(parent, -1);
        dist[0] = 0;

        // Step 3: Relax edges (n - 1) times
        for (int iter = 0; iter < n - 1; iter++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    double newDist = dist[u] + graph[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        parent[v] = u;
                    }
                }
            }
        }

        // Step 4: Check for negative cycle
        int cycleStart = -1;
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (dist[u] + graph[u][v] < dist[v]) {
                    parent[v] = u;
                    cycleStart = v;
                    break;
                }
            }
            if (cycleStart != -1) break;
        }

        if (cycleStart == -1) return null; // No arbitrage

        // Step 5: Recover cycle
        // Move cycleStart forward n times to guarantee it's inside cycle
        for (int i = 0; i < n; i++) {
            cycleStart = parent[cycleStart];
        }

        List<Integer> cycle = new ArrayList<>();
        int curr = cycleStart;

        do {
            cycle.add(curr);
            curr = parent[curr];
        } while (curr != cycleStart);

        cycle.add(cycleStart); // close the loop
        Collections.reverse(cycle);

        return cycle;
    }

    public static void main(String[] args) {

        List<List<Double>> exchangeRates = Arrays.asList(
                Arrays.asList(1.0, 0.82, 1.2),
                Arrays.asList(1.22, 1.0, 1.46),
                Arrays.asList(0.83, 0.68, 1.0)
        );

        List<Integer> cycle = findArbitrageCycle(exchangeRates);

        if (cycle == null) {
            System.out.println("No arbitrage found.");
        } else {
            System.out.println("Arbitrage cycle found:");

            for (int i = 0; i < cycle.size(); i++) {
                System.out.print(cycle.get(i));
                if (i < cycle.size() - 1) System.out.print(" → ");
            }
        }
    }


//    public static void main(String[] args) {
//        List<List<Double>> exchangeRates = Arrays.asList(
//                Arrays.asList(1.0, 0.82, 1.2),
//                Arrays.asList(1.22, 1.0, 1.46),
//                Arrays.asList(0.83, 0.68, 1.0)
//        );
//
//        System.out.println(detectArbitrage(exchangeRates));
//    }
}

