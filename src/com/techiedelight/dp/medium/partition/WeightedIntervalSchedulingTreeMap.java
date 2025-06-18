package com.techiedelight.dp.medium.partition;

import java.util.*;

public class WeightedIntervalSchedulingTreeMap {

    static class Job {
        int start, end, profit;

        Job(int s, int e, int p) {
            start = s;
            end = e;
            profit = p;
        }

        @Override
        public String toString() {
            return "Job{start=" + start + ", end=" + end + ", profit=" + profit + "}";
        }
    }

    public static int jobScheduling(int[] start, int[] end, int[] profit) {
        int n = start.length;
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(start[i], end[i], profit[i]);
        }

        Arrays.sort(jobs, Comparator.comparingInt(j -> j.end));

        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0); // time 0 -> profit 0

        // map from job end time to the selected job at that point
        Map<Integer, Job> selectedJobMap = new HashMap<>();

        for (Job job : jobs) {
            // Find best profit before this job starts
            int prevProfit = dp.floorEntry(job.start).getValue();
            int currentProfit = prevProfit + job.profit;

            // Update TreeMap if this profit is better
            if (currentProfit > dp.lastEntry().getValue()) {
                dp.put(job.end, currentProfit);
                selectedJobMap.put(job.end, job);
            }
        }

        // Reconstruct selected jobs
        System.out.println("Selected Jobs:");
        List<Job> selectedJobs = new ArrayList<>();
        Integer currentTime = dp.lastKey();
        while (currentTime != null && currentTime > 0) {
            Job job = selectedJobMap.get(currentTime);
            if (job != null) {
                selectedJobs.add(job);
                // Move to the previous compatible job's end time
                currentTime = dp.floorKey(job.start);
            } else {
                break;
            }
        }

        Collections.reverse(selectedJobs); // restore chronological order
        for (Job job : selectedJobs) {
            System.out.println(job);
        }

        return dp.lastEntry().getValue();
    }

    public static void main(String[] args) {
        int[] start = {1, 2, 3, 3};
        int[] end = {3, 4, 5, 6};
        int[] profit = {50, 10, 40, 70};
        int maxProfit = jobScheduling(start, end, profit);
        System.out.println("Maximum Profit: " + maxProfit);
    }
}

