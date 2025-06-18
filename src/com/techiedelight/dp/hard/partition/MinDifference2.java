package com.techiedelight.dp.hard.partition;

import java.util.HashMap;

public class MinDifference2 {

    private static HashMap<String, Integer> memo = new HashMap<>();
    // Partition set `S` into two subsets, `S1` and `S2`, such that the
    // difference between the sum of elements in `S1` and the sum
    // of elements in `S2` is minimized
    public static int findMinAbsDiff(int[] S, int n, int S1, int S2) {
        // Base case: if the list becomes empty, return the absolute
        // difference between both sets
        if (n < 0) {
            return Math.abs(S1 - S2);
        }

        // Create a unique key for memoization based on current state
        String key = n + "-" + S1 + "-" + S2;

        // Check if the result for the current state is already computed
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Case 1: Include the current item in subset `S1`
        int inc = findMinAbsDiff(S, n - 1, S1 + S[n], S2);

        // Case 2: Exclude the current item from subset `S1` and add it to `S2`
        int exc = findMinAbsDiff(S, n - 1, S1, S2 + S[n]);

        // Take the minimum of the two cases and store it in the memoization map
        int result = Math.min(inc, exc);
        memo.put(key, result);
        return result;
    }

    private static int findMinDiffArrayHelper(int[] arr, int index, int currentSum, int totalSum, Integer[][] memo) {
        // Base case: if we've considered all elements
        if (index == arr.length) {
            return Math.abs(totalSum - 2 * currentSum);
        }

        // Check if this subproblem has already been solved
        if (memo[index][currentSum] != null) {
            return memo[index][currentSum];
        }
        // Include current element in subset1
        int includeItem = findMinDiffArrayHelper(arr, index + 1, currentSum + arr[index], totalSum, memo);
        // Exclude current element from subset1
        int excludeItem = findMinDiffArrayHelper(arr, index + 1, currentSum, totalSum, memo);

        // Store the minimum result in memo
        memo[index][currentSum] = Math.min(includeItem, excludeItem);

        return memo[index][currentSum];
    }

    public static int findMinDiffWithArrayMemo(int[] arr) {
        int n = arr.length;
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        // Create memo array: memo[index][sum] = minimum difference possible
        // We only need to track sums up to totalSum/2
        Integer[][] memo = new Integer[n][totalSum + 1];

        return findMinDiffArrayHelper(arr, 0, 0, totalSum, memo);
    }

    public static void main(String[] args) {
        // Input: a set of items
        int[] S = {1, 6, 11, 5};
        // Call the recursive function with initial sums 0 for both subsets
        System.out.println("The minimum difference is " + findMinAbsDiff(S, S.length - 1, 0, 0));
    }
}
