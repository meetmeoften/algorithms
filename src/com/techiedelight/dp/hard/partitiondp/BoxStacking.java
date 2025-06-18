package com.techiedelight.dp.hard.partitiondp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoxStacking {

    class Box implements Comparable<Box> {
        int height, width, depth;

        public Box(int h, int w, int d) {
            this.height = h;
            this.width = Math.max(w, d);  // Ensure width >= depth
            this.depth = Math.min(w, d);
        }

        public int baseArea() {
            return this.width * this.depth;
        }

        @Override
        public int compareTo(Box other) {
            return other.baseArea() - this.baseArea();  // Descending order
        }
    }

    public int maxStackHeight(int[][] inputBoxes) {
        List<Box> boxes = new ArrayList<>();

        // Step 1: Generate all rotations
        for (int[] dims : inputBoxes) {
            int h = dims[0], w = dims[1], d = dims[2];
            boxes.add(new Box(h, w, d));
            boxes.add(new Box(w, h, d));
            boxes.add(new Box(d, h, w));
        }

        // Step 2: Sort boxes by base area descending
        Collections.sort(boxes);

        int n = boxes.size();
        int[] dp = new int[n];  // dp[i] = max height with box i at the bottom

        // Step 3: Initialize DP with each box's own height
        for (int i = 0; i < n; i++) {
            dp[i] = boxes.get(i).height;
        }

        // Step 4: Compute max stack height for each box
        for (int i = 1; i < n; i++) {
            Box curr = boxes.get(i);
            for (int j = 0; j < i; j++) {
                Box prev = boxes.get(j);
                if (curr.width < prev.width && curr.depth < prev.depth) {
                    dp[i] = Math.max(dp[i], dp[j] + curr.height);
                }
            }
        }

        // Step 5: Find max in dp[]
        int maxHeight = 0;
        for (int h : dp) maxHeight = Math.max(maxHeight, h);

        return maxHeight;
    }

    public static void main(String[] args) {
        BoxStacking solver = new BoxStacking();
        int[][] boxes = {
                {4, 6, 7},
                {1, 2, 3},
                {4, 5, 6},
                {10, 12, 32}
        };
        System.out.println("Maximum stack height: " + solver.maxStackHeight(boxes));
    }
}

