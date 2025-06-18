package com.techiedelight.dp.hard.tree;

public class OptimalBST {

    public static int findOptimalCost(int[] freq, int start, int end, int level) {
        if (start > end) {
            return 0;
        }
        int optimalCost = Integer.MAX_VALUE;
        for (int k = start; k <= end; k++) {
            int left = findOptimalCost(freq, start, k - 1, level + 1);
            int right = findOptimalCost(freq, k + 1, end, level + 1);
            int cost = freq[k] * level + left + right;
            // update the optimal cost
            optimalCost = Integer.min(optimalCost, cost);
        }
        return optimalCost;
    }

    public static void main(String[] args) {
        int[] freq = { 25, 10, 20 };

        System.out.println("The optimal cost of constructing BST is "
                + findOptimalCost(freq, 0, freq.length - 1, 1));
    }
}
