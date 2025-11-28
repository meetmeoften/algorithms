package com.test.binaryTree;

import java.util.*;

public class MinimumTimeToBurnBinaryTree {

    // Build parent references and find the target node
    private static TreeNode buildParentMap(TreeNode root, int target,
                                           Map<TreeNode, TreeNode> parentMap) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        parentMap.put(root, null);

        TreeNode targetNode = null;

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur.value == target) targetNode = cur;

            if (cur.left != null) {
                parentMap.put(cur.left, cur);
                q.offer(cur.left);
            }
            if (cur.right != null) {
                parentMap.put(cur.right, cur);
                q.offer(cur.right);
            }
        }
        return targetNode;
    }

    public static int minTimeToBurn(TreeNode root, int target) {
        if (root == null) return 0;

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();

        // Step 1: build parent links & find target
        TreeNode targetNode = buildParentMap(root, target, parentMap);
        if (targetNode == null) return 0;

        // Step 2: BFS to simulate burning
        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        q.offer(targetNode);
        visited.add(targetNode);

        int time = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            boolean anyBurnedThisSecond = false;

            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();

                // Try to burn left
                if (cur.left != null && !visited.contains(cur.left)) {
                    visited.add(cur.left);
                    q.offer(cur.left);
                    anyBurnedThisSecond = true;
                }

                // Try to burn right
                if (cur.right != null && !visited.contains(cur.right)) {
                    visited.add(cur.right);
                    q.offer(cur.right);
                    anyBurnedThisSecond = true;
                }

                // Try to burn parent
                TreeNode parent = parentMap.get(cur);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    q.offer(parent);
                    anyBurnedThisSecond = true;
                }
            }

            if (anyBurnedThisSecond) time++;
        }

        return time;
    }

    // Quick test
    public static void main(String[] args) {
        /*
                 1
               /   \
              2     3
             / \   / \
            4  5  6   7
                   \
                    8

            Burn starting from 5
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.left.right = new TreeNode(8);

        System.out.println(minTimeToBurn(root, 5));  // Output: 4
    }
}

