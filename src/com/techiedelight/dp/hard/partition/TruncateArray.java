package com.techiedelight.dp.hard.partition;

import java.util.*;

public class TruncateArray {

    // Memoization map to store the minimum removals for subarrays (i, j)
    private static Map<String, Integer> memo = new HashMap<>();

    public static int findMin(List<Integer> input, int low, int high) {
        // Base case: If the range is invalid, return 0 removals
        if (low > high) {
            return 0;
        }

        // Check if the result for this subarray is already calculated
        String key = low + "-" + high;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Find the minimum and maximum elements in the current subarray
        int min = input.subList(low, high + 1).stream().min(Comparator.naturalOrder()).get();
        int max = input.subList(low, high + 1).stream().max(Comparator.naturalOrder()).get();

        // If 2 * min <= max, we need to remove elements and try again
        if (2 * min <= max) {
            // Remove from the left or right, and take the minimum number of removals
            int removeLeft = 1 + findMin(input, low + 1, high);
            int removeRight = 1 + findMin(input, low, high - 1);
            int result = Math.min(removeLeft, removeRight);

            // Store the result in the memo map and return it
            memo.put(key, result);
            return result;
        }

        // If 2 * min > max, no removals are needed
        memo.put(key, 0);
        return 0;
    }

    public static int findMin(List<Integer> input) {
        return findMin(input, 0, input.size() - 1);
    }

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(4, 6, 1, 7, 5, 9, 2);
        System.out.println("The minimum number of removals is " + findMin(input));
    }
}

