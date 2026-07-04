package com.algoexpert.binarytree;

public class BinaryTreeCameras {

    public int cam = 0;

    public int minCameraCover(TreeNode root) {
        if (root == null) return 0;
        int top = dfs(root);
        return cam + (top == 0 ? 1 : 0);
    }

    public int dfs(TreeNode root) {
        if(root == null) return 1;
        int left = dfs(root.left);
        int right = dfs(root.right);

        if(left == 0 || right == 0) {
            cam++;
            return 2;
        }

        if(left == 2 || right == 2) {
            return 1;
        }

        return 0;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println(new BinaryTreeCameras().minCameraCover(root));
    }
}
