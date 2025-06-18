package com.techiedelight.dp.medium.stairs;

import java.util.HashMap;
import java.util.Map;

public class HatDearrangement {

    public static void main(String[] args) {
        int n = 5;  // Example for n = 5
        System.out.println("Number of derangements: " + countDerangements(n));
    }

    public static int countDerangements(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        return derangements(n, memo);
    }

    private static int derangements(int n, Map<Integer, Integer> memo) {
        // Base cases
        if (n == 0) return 1;  // D(0) = 1
        if (n == 1) return 0;  // D(1) = 0
        if (n == 2) return 1;  // D(2) = 1

        if (memo.containsKey(n)) return memo.get(n);

        // Recurrence relation: D(n) = (n - 1) * (D(n - 1) + D(n - 2))
        int result = (n - 1) * (derangements(n - 1, memo) + derangements(n - 2, memo));
        memo.put(n, result);

        return result;
    }
}


