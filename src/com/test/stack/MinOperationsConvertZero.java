package com.test.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MinOperationsConvertZero {

    public static int minOperations(int[] nums) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);  // sentinel

        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() > num) {
                stack.pop();
            }
            if (stack.isEmpty() || stack.peek() < num) {
                ans++;
                stack.push(num);
            }
            // else stack.peek() == num → do nothing extra
        }
        return ans;
    }

    public static void main(String[] args) {
        minOperations(new int[]{3, 1, 2, 1});
    }
}
