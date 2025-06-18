package com.techiedelight.binarytree.hard;

import com.algoexpert.binarytree.view.Node;

public class MaximumPathSum {

    int maxSum = Integer.MIN_VALUE;

    public int findMaxPathSum(Node node) {
        maxPathDown(node);
        return maxSum;
    }

    private int maxPathDown(Node node) {
        if (node == null) return 0;

        // Recursively get max path sum from left and right
        int left = Math.max(0, maxPathDown(node.left));   // ignore if < 0
        int right = Math.max(0, maxPathDown(node.right)); // ignore if < 0

        // Path going through this node (could be root of max path)
        int currentPath = node.data + left + right;
        // Update global max
        maxSum = Math.max(maxSum, currentPath);
        // Return max single path (cannot split for parent recursion)
        return node.data + Math.max(left, right);
    }

    // Two leaves
    int maxSumLeaves = Integer.MIN_VALUE;
    public int maxPathSumBetweenLeaves(Node root) {
        if (root == null) {
            return 0;
        }

        maxPathSumBetweenLeavesHelper(root);
        return maxSumLeaves;
    }

    private int maxPathSumBetweenLeavesHelper(Node node) {
        if (node == null) {
            return 0;
        }
        int left = maxPathSumBetweenLeavesHelper(node.left);
        int right = maxPathSumBetweenLeavesHelper(node.right);

        if (node.left == null && node.right == null) {
            return node.data;
        }
        if (node.left != null && node.right != null) {
            int curr = left + right + node.data;
            maxSumLeaves = Math.max(curr, maxSumLeaves);
            return Math.max(left, right) + node.data;
        }
        return (node.left != null ? left : right) + node.data;

    }
}
