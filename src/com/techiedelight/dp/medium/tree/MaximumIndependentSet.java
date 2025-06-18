package com.techiedelight.dp.medium.tree;

import java.util.HashMap;
import java.util.Map;

public class MaximumIndependentSet {


    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // Constructing the binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(5);
//        root.right.right = new TreeNode(6);

        int maxIndependentSet = findMaxIndependentSet(root);
        System.out.println("Maximum Independent Set Size: " + maxIndependentSet);
    }

    public static int findMaxIndependentSet(TreeNode root) {
        int[] result = findMaxIndependentSetUtil(root);
        System.out.println(Math.max(result[0], result[1]));
        int result2 = findMaxIndependentSetUtil2(root, new HashMap<>());
        System.out.println(result2);
        return result2;
    }

    private static int findMaxIndependentSetUtil2(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        if (map.get(root) != null) {
            return map.get(root);
        }
        // Case 1: Exclude the current node from the maximum independent set and
        // recur for its left and right child
        int excl = findMaxIndependentSetUtil2(root.left, map) + findMaxIndependentSetUtil2(root.right, map);
        // Case 2: Include the current node in the maximum independent set and
        // recur for its grandchildren
        int incl = 1;
        if (root.left != null) {
            incl += findMaxIndependentSetUtil2(root.left.left, map) + findMaxIndependentSetUtil2(root.left.right, map);
        }
        if (root.right != null) {
            incl += findMaxIndependentSetUtil2(root.right.left, map) + findMaxIndependentSetUtil2(root.right.right, map);
        }
        // save and return the maximum number of nodes possible by either
        // including or excluding the current node
        map.put(root, Integer.max(excl, incl));
        return map.get(root);
    }

    private static int[] findMaxIndependentSetUtil(TreeNode node) {
        // Base case: If the node is null, return {0, 0}
        if (node == null) {
            return new int[]{0, 0};
        }

        // Recursively calculate the MIS for the left and right subtrees
        int[] left = findMaxIndependentSetUtil(node.left);
        int[] right = findMaxIndependentSetUtil(node.right);

        // dp[node][0] = Maximum MIS size excluding the current node
        int excludeNode = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        // dp[node][1] = Maximum MIS size including the current node
        int includeNode = 1 + left[0] + right[0];  // Include current node and exclude children
        //int includeNode = node.val + left[0] + right[0];  // Include current node and exclude children

        // Return the result for the current node
        return new int[]{excludeNode, includeNode};
    }
}
