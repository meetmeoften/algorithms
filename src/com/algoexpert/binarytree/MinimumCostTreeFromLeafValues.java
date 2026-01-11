package com.algoexpert.binarytree;

import com.sun.source.tree.Tree;

import java.util.Stack;

public class MinimumCostTreeFromLeafValues {
    private int[] arr;
    private int[][] memo;
    private int[][] max;
    private int n;


    /**
     * Key Observation
     * <p>
     * Always merge the smallest leaf with the smaller neighbor to minimize cost.
     * <p>
     * Time & Space
     * <p>
     * Time: O(n)
     * <p>
     * Space: O(n)
     *
     * @param arr
     * @return
     */
    public int mctFromLeafValues(int[] arr) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);

        for (int num : arr) {
            while (stack.peek() <= num) {
                int mid = stack.pop();
                res += mid * Math.min(stack.peek(), num);
            }
            stack.push(num);
        }

        while (stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }

        return res;
    }


    public int mctFromLeafValues2(int[] arr) {
        this.arr = arr;
        this.n = arr.length;
        this.memo = new int[n][n];
        this.max = new int[n][n];

        // Precompute max values
        for (int i = 0; i < n; i++) {
            max[i][i] = arr[i];
            for (int j = i + 1; j < n; j++) {
                max[i][j] = Math.max(max[i][j - 1], arr[j]);
            }
        }

        return dfs(0, n - 1);
    }

    private int dfs(int l, int r) {
        if (l == r) return 0;
        if (memo[l][r] != 0) return memo[l][r];

        int res = Integer.MAX_VALUE;

        for (int k = l; k < r; k++) {
            res = Math.min(res,
                    dfs(l, k) + dfs(k + 1, r)
                            + max[l][k] * max[k + 1][r]
            );
        }

        memo[l][r] = res;
        return res;
    }

    public static void main(String[] args) {
        MinimumCostTreeFromLeafValues game = new MinimumCostTreeFromLeafValues();
        System.out.println(game.mctFromLeafValues(new int[]{6, 2, 4}));
    }

}
