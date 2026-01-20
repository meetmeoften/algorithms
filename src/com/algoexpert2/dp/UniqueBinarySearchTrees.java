package com.algoexpert2.dp;

import com.algoexpert.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueBinarySearchTrees {


    private Map<String, List<TreeNode>> memo = new HashMap<>();

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return build(1, n);
    }

    private List<TreeNode> build(int start, int end) {
        String key = start + "," + end;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        List<TreeNode> result = new ArrayList<>();

        // Base case: empty tree
        if (start > end) {
            result.add(null);
            return result;
        }

        for (int root = start; root <= end; root++) {
            List<TreeNode> leftTrees = build(start, root - 1);
            List<TreeNode> rightTrees = build(root + 1, end);

            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode node = new TreeNode(root);
                    node.left = left;
                    node.right = right;
                    result.add(node);
                }
            }
        }

        memo.put(key, result);
        return result;
    }


    public static void main(String[] args) {
        UniqueBinarySearchTrees uniqueBinarySearchTrees = new UniqueBinarySearchTrees();
        uniqueBinarySearchTrees.generateTrees(3);
    }
}

