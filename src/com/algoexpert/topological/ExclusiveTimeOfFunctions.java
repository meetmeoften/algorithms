package com.algoexpert.topological;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ExclusiveTimeOfFunctions {

    public static int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        int prevTime = 0;

        for (String log : logs) {
            String[] parts = log.split(":");
            int id = Integer.parseInt(parts[0]);
            String type = parts[1];
            int time = Integer.parseInt(parts[2]);

            if (type.equals("start")) {
                if (!stack.isEmpty()) {
                    result[stack.peek()] += time - prevTime;
                }
                stack.push(id);
                prevTime = time;
            } else { // end
                result[stack.peek()] += time - prevTime + 1;
                stack.pop();
                prevTime = time + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 2;
        List<String> logs = Arrays.asList(
                "0:start:0",
                "1:start:2",
                "1:end:5",
                "0:end:6"
        );
        int[] ans = exclusiveTime(n, logs);
        System.out.println(Arrays.toString(ans));
    }
}
