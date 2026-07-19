package com.algoexpert.topological;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Nested {

    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it in LeetCode.
     */

    public int nestedWeightListSum1(List<NestedInteger> nestedList) {
        return dfs(nestedList, 0);
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
        int depthSum = 0;
        for (NestedInteger item : nestedList) {
            if (item.isInteger()) {
                depthSum += item.getInteger() * depth;
            } else {
                depthSum += dfs(item.getList(), depth + 1);
            }
        }
        return depthSum;
    }

    public int nestedWeightListSum1BFS(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>(nestedList);
        int depth = 1;
        int sum = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i= 0; i < size; i++) {
                NestedInteger ni = queue.poll();
                if (ni.isInteger()) {
                    sum += ni.getInteger() * depth;
                } else {
                    queue.addAll(ni.getList());
                }
            }
            depth++;
        }
        return sum;
    }

    //=============================

    public int depthSumInverseBFS(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>(nestedList);
        int unweighted = 0;
        int weighted = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger current = queue.poll();
                if (current.isInteger()) {
                    unweighted += current.getInteger();
                } else {
                    queue.addAll(current.getList());
                }
            }
            weighted += unweighted;
        }
        return weighted;
    }

    // -------------------------------------

    public int depthSumInverse2(List<NestedInteger> nestedList) {
        int maxDepth = maxDepth(nestedList);
        return dfsInverse(nestedList, 1, maxDepth);
    }

    private int maxDepth(List<NestedInteger> nestedList) {
        int depth = 1;
        for (NestedInteger item : nestedList) {
            if (item.isInteger()) {
                continue;
            }
            depth = Math.max(depth, 1 + maxDepth(item.getList()));
        }
        return depth;
    }

    private int dfsInverse(List<NestedInteger> list, int depth, int maxDepth) {
        int sum = 0;
        for (NestedInteger ni : list) {
            if (ni.isInteger()) {
                sum += ni.getInteger() * (maxDepth - depth + 1);
            } else {
                sum += dfsInverse(ni.getList(), depth + 1, maxDepth);
            }
        }
        return sum;
    }

    // ---------------- MAIN METHOD FOR DEMO ----------------

    public static void main(String[] args) {
        /*
            Example:
            [1,[4,[6]]]

            Expected Output:
            17
         */
        NestedInteger n1 = new NI(1);
        NestedInteger n6 = new NI(6);
        NestedInteger list6 = new NI(Arrays.asList(n6));
        NestedInteger n4 = new NI(4);
        NestedInteger list4 = new NI(Arrays.asList(n4, list6));
        List<NestedInteger> input = Arrays.asList(n1, list4);
        Nested sol = new Nested();
        int result = sol.depthSumInverseBFS(input);
        System.out.println("Result = " + result);
        int result1 = sol.depthSumInverse2(input);
        System.out.println("Result = " + result1);
    }
}

/* ---------------- Helper Implementation ---------------- */


