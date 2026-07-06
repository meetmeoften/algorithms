package com.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ConflictingPair {

    public static long maxSubarrays(int n, int[][] pairs) {

        List<int[]>[] conflicts = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            conflicts[i] = new ArrayList<>();
        }

        for (int i = 0; i < pairs.length; i++) {

            int a = Math.min(pairs[i][0], pairs[i][1]);
            int b = Math.max(pairs[i][0], pairs[i][1]);

            conflicts[a].add(new int[]{b, i});
        }

        long base = 0;

        long[] gain = new long[pairs.length];

        int firstMin = n + 1;
        int secondMin = n + 1;

        int firstPair = -1;

        PriorityQueue<int[]> pq =
                new PriorityQueue<>((x, y) -> x[0] - y[0]);

        for (int left = n; left >= 1; left--) {

            for (int[] edge : conflicts[left]) {
                pq.offer(edge);
            }

            while (pq.size() > 0 &&
                    pq.peek()[0] <= left) {
                pq.poll();
            }

            firstMin = n + 1;
            secondMin = n + 1;
            firstPair = -1;

            if (!pq.isEmpty()) {

                int[] first = pq.poll();

                firstMin = first[0];
                firstPair = first[1];

                if (!pq.isEmpty()) {
                    secondMin = pq.peek()[0];
                }

                pq.offer(first);
            }

            base += firstMin - left;

            if (firstPair != -1) {
                gain[firstPair] +=
                        (long) (secondMin - firstMin);
            }
        }

        long bestGain = 0;

        for (long g : gain) {
            bestGain = Math.max(bestGain, g);
        }

        return base + bestGain;
    }

    public static void main(String[] args) {

        int n = 4;

        int[][] pairs = {
                {2, 3},
                {1, 4}
        };

        System.out.println(
                maxSubarrays(n, pairs)
        );
    }
}