package com.algoexpert.topological;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRangeCoveringKElements {

    public int[] smallestRange(List<List<Integer>> nums) {

        // {value, listIndex, elementIndex}
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a[0], b[0])
        );

        int max = Integer.MIN_VALUE;

        // Push the first element of every list
        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i).get(0);
            pq.offer(new int[]{val, i, 0});
            max = Math.max(max, val);
        }

        int start = 0;
        int end = Integer.MAX_VALUE;

        while (pq.size() == nums.size()) {

            int[] curr = pq.poll();

            int min = curr[0];
            int list = curr[1];
            int idx = curr[2];

            // Update answer if current range is smaller
            if (max - min < end - start) {
                start = min;
                end = max;
            }

            // Move to next element in the same list
            if (idx + 1 < nums.get(list).size()) {
                int nextVal = nums.get(list).get(idx + 1);

                pq.offer(new int[]{nextVal, list, idx + 1});
                max = Math.max(max, nextVal);
            } else {
                // One list is exhausted
                break;
            }
        }

        return new int[]{start, end};
    }

    public static void main(String[] args) {

        List<List<Integer>> nums = new ArrayList<>();

        nums.add(Arrays.asList(4, 10, 15, 24, 26));
        nums.add(Arrays.asList(0, 9, 12, 20));
        nums.add(Arrays.asList(5, 18, 22, 30));

        SmallestRangeCoveringKElements sol = new SmallestRangeCoveringKElements();
        int[] ans = sol.smallestRange(nums);

        System.out.println("Smallest Range: [" + ans[0] + ", " + ans[1] + "]");
    }

}
