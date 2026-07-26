package com.algoexpert.topological;

import java.sql.Time;
import java.util.PriorityQueue;

public class TimeToCrossBridge {

    public int findCrossingTime(int n, int k, int[][] time) {
        int answer = 0;
        int currentTime = 0;

// Workers currently doing tasks, ordered by finish time
        PriorityQueue<int[]> leftWorking = new PriorityQueue<>(
                (a, b) -> a[0] - b[0]
        );

        PriorityQueue<int[]> rightWorking = new PriorityQueue<>(
                (a, b) -> a[0] - b[0]
        );

// Workers waiting to cross, ordered by crossing priority
        PriorityQueue<int[]> leftWaiting = new PriorityQueue<>(
                (a, b) -> (a[0] != b[0]) ? b[0] - a[0] : b[1] - a[1]
        );

        PriorityQueue<int[]> rightWaiting = new PriorityQueue<>(
                (a, b) -> (a[0] != b[0]) ? b[0] - a[0] : b[1] - a[1]
        );

// Add all workers initially on the left side
        for (int worker = 0; worker < time.length; worker++) {
            int crossingTime = time[worker][0] + time[worker][2];
            leftWaiting.offer(new int[]{crossingTime, worker});
        }
        while (n > 0 || !rightWorking.isEmpty() || !rightWaiting.isEmpty()) {
            // If no worker can cross now, jump time to next available worker
            if (rightWaiting.isEmpty()
                    && (rightWorking.isEmpty() || rightWorking.peek()[0] > currentTime)
                    && (n == 0 || leftWaiting.isEmpty()
                    || leftWorking.isEmpty()
                    || leftWorking.peek()[0] > currentTime)) {
                int nextAvailableTime = Integer.MAX_VALUE;
                if (n > 0 && !leftWorking.isEmpty()) {
                    nextAvailableTime = Math.min(
                            nextAvailableTime,
                            leftWorking.peek()[0]
                    );
                }
                if (!rightWorking.isEmpty()) {
                    nextAvailableTime = Math.min(
                            nextAvailableTime,
                            rightWorking.peek()[0]
                    );
                }
                currentTime = nextAvailableTime;
            }
            // Workers who finished picking boxes become available on right side
            while (!leftWorking.isEmpty()
                    && leftWorking.peek()[0] <= currentTime) {
                int worker = leftWorking.poll()[1];
                int crossingPriority =
                        time[worker][0] + time[worker][2];
                rightWaiting.offer(new int[]{
                        crossingPriority,
                        worker
                });
            }
            // Workers who finished placing boxes become available on left side
            while (!rightWorking.isEmpty()
                    && rightWorking.peek()[0] <= currentTime) {
                int worker = rightWorking.poll()[1];
                int crossingPriority =
                        time[worker][0] + time[worker][2];
                leftWaiting.offer(new int[]{
                        crossingPriority,
                        worker
                });
            }
            // Right side has priority: bring a box back
            if (!rightWaiting.isEmpty()) {
                int worker = rightWaiting.poll()[1];
                currentTime += time[worker][2];
                if (n > 0) {
                    // Worker puts the box down on the left
                    leftWorking.offer(new int[]{
                            currentTime + time[worker][3],
                            worker
                    });
                } else {
                    // Last box has reached the left side
                    answer = Math.max(answer, currentTime);
                }
            } else {
                // Send a worker from left to right
                int worker = leftWaiting.poll()[1];
                currentTime += time[worker][0];
                // Worker picks a box on the right
                rightWorking.offer(new int[]{
                        currentTime + time[worker][1],
                        worker
                });
                n--;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        TimeToCrossBridge sol = new TimeToCrossBridge();
        int n = 1; // number of boxes
        int k = 3; // number of workers
        int[][] time = {
                {1, 1, 2, 1},
                {3, 2, 1, 2},
                {2, 3, 3, 1}
        };
        int result = sol.findCrossingTime(n, k, time);
        System.out.println("Time to cross bridge: " + result);
    }
}
