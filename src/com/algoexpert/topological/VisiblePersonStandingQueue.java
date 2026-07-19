package com.algoexpert.topological;

import java.util.Arrays;
import java.util.Stack;

public class VisiblePersonStandingQueue {

    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            int count = 0;
            while (!stack.isEmpty() && heights[i] > stack.peek()) {
                stack.pop();
                count++;
            }
            // First taller person
            if (!stack.isEmpty()) {
                count++;
            }
            ans[i] = count;
            stack.push(heights[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] heights = {10, 6, 8, 5, 11, 9};
        VisiblePersonStandingQueue sol = new VisiblePersonStandingQueue();
        int[] ans = sol.canSeePersonsCount(heights);
        System.out.println(Arrays.toString(ans));
    }
}
