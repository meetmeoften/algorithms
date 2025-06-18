package com.algoexpert.binarysearch;

public class MaxDistanceBetweenGasStations {

    public static double minmaxGasDist(int[] stations, int k) {
        double low = 0;
        double high = stations[stations.length - 1] - stations[0];
        double eps = 1e-6; // acceptable error

        while (high - low > eps) {
            double mid = (low + high) / 2;
            System.out.println(mid);
            if (canPlace(stations, k, mid)) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return low;
    }

    private static boolean canPlace(int[] stations, int k, double dist) {
        int count = 0;
        for (int i = 1; i < stations.length; i++) {
            double gap = stations[i] - stations[i - 1];
            count += (int)(gap / dist);
        }
        return count <= k;
    }

    public static void main(String[] args) {
        // Example input: sorted gas station positions
        int[] stations = {1, 5, 7};
        int K = 1; // Number of additional gas stations you can add

        double result = minmaxGasDist(stations, K);
        System.out.printf("Minimum possible maximum distance: %.6f\n", result);
    }
}
