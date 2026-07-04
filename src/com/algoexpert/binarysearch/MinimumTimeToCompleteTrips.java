package com.algoexpert.binarysearch;

public class MinimumTimeToCompleteTrips {


    public static long minimumTime2(int[] time, int totalTrips) {
        long currentTime = 1;

        while (true) {
            long trips = 0;
            for (int t : time) {
                trips += currentTime / t;
            }
            if (trips >= totalTrips) {
                return currentTime;
            }
            currentTime++;
        }
    }

    public long minimumTime(int[] time, int totalTrips) {
        long left = 1;
        long right = (long) getMin(time) * totalTrips;

        while (left < right) {
            long mid = left + (right - left) / 2;

            if (canComplete(time, mid, totalTrips)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canComplete(int[] time, long currentTime, int totalTrips) {
        long trips = 0;
        for (int t : time) {
            trips += currentTime / t;
            if (trips >= totalTrips) {
                return true; // early exit to avoid overflow
            }
        }
        return false;
    }

    private int getMin(int[] time) {
        int min = Integer.MAX_VALUE;
        for (int t : time) {
            min = Math.min(min, t);
        }
        return min;
    }


    public static void main(String[] args) {

        int[] time = { 1, 2, 3 };
        int totalTrips = 5;
        MinimumTimeToCompleteTrips minimumTimeToCompleteTrips = new MinimumTimeToCompleteTrips();
        minimumTimeToCompleteTrips.minimumTime(time, totalTrips);
    }

}
