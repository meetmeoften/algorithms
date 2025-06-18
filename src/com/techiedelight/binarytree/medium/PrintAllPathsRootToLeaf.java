package com.techiedelight.binarytree.medium;

import com.algoexpert.binarytree.view.Node;

import java.util.ArrayList;
import java.util.List;

public class PrintAllPathsRootToLeaf {

    public void printAllPaths(Node root) {
        List<Integer> path = new ArrayList<>();
        printPathsHelper(root, path);
    }

    private void printPathsHelper(Node root, List<Integer> paths) {
        if (root == null) return;
        paths.add(root.data);
        if (root.left == null && root.right == null) {
            System.out.println(new ArrayList<>(paths));
        } else {
            printPathsHelper(root.left, paths);
            printPathsHelper(root.right, paths);
        }
        paths.remove(paths.size() - 1);
    }


    public boolean hasPathSum(Node root, int targetSum) {
        if (root == null)
            return false;

        // Check if it's a leaf node
        if (root.left == null && root.right == null)
            return (targetSum == root.data);

        // Recurse on left and right with reduced sum
        int remainingSum = targetSum - root.data;
        return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum);
    }

    private void findPathHelper(Node node, int currentSum, List<Integer> path, Result res) {
        if (node == null) return;

        currentSum += node.data;
        path.add(node.data);

        // If it's a leaf node
        if (node.left == null && node.right == null) {
            if (currentSum > res.maxSum) {
                res.maxSum = currentSum;
                res.maxPath = new ArrayList<>(path);
            }
        } else {
            // Continue to left and right subtrees
            findPathHelper(node.left, currentSum, path, res);
            findPathHelper(node.right, currentSum, path, res);
        }

        // Backtrack
        path.remove(path.size() - 1);
    }

    static class Result {
        int maxSum = Integer.MIN_VALUE;
        List<Integer> maxPath = new ArrayList<>();
    }
}
