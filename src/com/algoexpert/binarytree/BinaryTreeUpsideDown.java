package com.algoexpert.binarytree;

public class BinaryTreeUpsideDown {

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.right = root;
        root.left.left = root.right;
        root.left = null;
        root.right = null;
        return newRoot;
    }

    public TreeNode upsideDownBinaryTree2(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null;
        TreeNode prevRight = null;

        while (curr != null) {
            TreeNode next = curr.left;

            curr.left = prevRight;
            prevRight = curr.right;

            curr.right = prev;
            prev = curr;

            curr = next;
        }

        return prev;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        // root.left.left = new TreeNode(4);
        //root.left.right = new TreeNode(5);

        System.out.println(new BinaryTreeUpsideDown().upsideDownBinaryTree2(root));
    }
}
