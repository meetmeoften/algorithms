package com.techiedelight.dp.medium.partition;

public class Knapsack {

    public static int knapsackRecursive(int[] weights, int[] values, int i, int capacity) {
        if (capacity < 0) {
            return Integer.MIN_VALUE;
        }
        if (i < 0 || capacity == 0) {
            return 0;
        }
        int exclude = knapsackRecursive(weights, values, i - 1, capacity);
//        int include = Integer.MIN_VALUE;
//        if(weights[i] <= capacity) {
//            include = values[i] + knapsackRecursive(weights, values, i-1, capacity - weights[i]);
//        }
        int include = values[i] + knapsackRecursive(weights, values, i - 1, capacity - weights[i]);
        return Math.max(include, exclude);


    }
}
