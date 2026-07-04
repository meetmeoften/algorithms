package com.algoexpert.dp;

import java.util.PriorityQueue;

public class CrossingTime {


    public int findCrossingTime(int n, int k, int[][] time) {

        // less efficient worker first
        PriorityQueue<Integer> waitLeft = new PriorityQueue<>((a, b) -> {
            int ea = time[a][0] + time[a][2];
            int eb = time[b][0] + time[b][2];

            if (ea != eb) {
                return eb - ea;
            }

            return b - a;
        });

        PriorityQueue<Integer> waitRight = new PriorityQueue<>((a, b) -> {
            int ea = time[a][0] + time[a][2];
            int eb = time[b][0] + time[b][2];

            if (ea != eb) {
                return eb - ea;
            }

            return b - a;
        });

        // worker becomes free on left
        PriorityQueue<int[]> workLeft = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        // worker becomes free on right
        PriorityQueue<int[]> workRight = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        for (int i = 0; i < k; i++) {
            waitLeft.offer(i);
        }

        int currentTime = 0;

        while (n > 0 || !waitRight.isEmpty() || !workRight.isEmpty()) {

            // move finished workers to waiting queues
            while (!workLeft.isEmpty() && workLeft.peek()[0] <= currentTime) {
                waitLeft.offer(workLeft.poll()[1]);
            }

            while (!workRight.isEmpty() && workRight.peek()[0] <= currentTime) {
                waitRight.offer(workRight.poll()[1]);
            }

            // priority 1: worker on right returns with box
            if (!waitRight.isEmpty()) {
                int worker = waitRight.poll();

                currentTime += time[worker][2]; // right to left

                workLeft.offer(new int[]{
                        currentTime + time[worker][3], // finish putting box
                        worker
                });
            }

            // priority 2: send worker from left to right if boxes remain
            else if (n > 0 && !waitLeft.isEmpty()) {
                int worker = waitLeft.poll();

                currentTime += time[worker][0]; // left to right

                workRight.offer(new int[]{
                        currentTime + time[worker][1], // finish picking box
                        worker
                });

                n--;
            }

            // no worker available, jump time
            else {
                int nextTime = Integer.MAX_VALUE;

                if (!workLeft.isEmpty()) {
                    nextTime = Math.min(nextTime, workLeft.peek()[0]);
                }

                if (!workRight.isEmpty()) {
                    nextTime = Math.min(nextTime, workRight.peek()[0]);
                }

                currentTime = nextTime;
            }
        }

        return currentTime;
    }

    public static void main(String[] args) {

        int n = 1;
        int k = 3;

        int[][] time = {
                {1, 1, 2, 1},
                {1, 1, 3, 1},
                {1, 1, 4, 1}
        };
        CrossingTime solution = new CrossingTime();
        int result = solution.findCrossingTime(n, k, time);

        System.out.println(result);
    }
}
