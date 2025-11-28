package com.google;

import com.algoexpert.binarytree.TreeNode;

public class StepByStepDirectionsBinaryTreeNode {

    public String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode parent = lca(root, startValue, destValue);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        findP(parent, sb, startValue);
        findQ(parent, sb2, destValue);
        return sb.append(sb2).toString();
    }

    public TreeNode lca(TreeNode root, int node1, int node2) {
        if (root == null || root.val == node1 || root.val == node2)  return root;
        TreeNode left = lca(root.left, node1, node2);
        TreeNode right = lca(root.right, node1, node2);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    public boolean findP(TreeNode root, StringBuilder sb, int p) {
        if (root == null) return false;
        if (root.val == p) return true;
        if (findP(root.left, sb, p) || findP(root.right, sb, p)) {
            sb.append('U');
            return true;
        }
        return false;
    }

    private boolean findQ(TreeNode root, StringBuilder sb, int q) {
        if (root == null) return false;
        if (root.val == q) return true;
        if (findQ(root.left, sb, q)) {
            sb.insert(0, 'L');
            return true;
        }
        if (findQ(root.right, sb, q)) {
            sb.insert(0, 'R');
            return true;
        }
        return false;
    }
}
