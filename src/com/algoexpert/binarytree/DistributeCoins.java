package com.algoexpert.binarytree;

public class DistributeCoins {

    public int moves = 0;

    public int distributeCoins(TreeNode root) {
        int val = dfs(root);
        return  moves;
    }

    public int dfs(TreeNode root) {
        if(root == null ) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);

        moves += Math.abs(left) + Math.abs(right);
        return root.val + left + right - 1;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);

        System.out.println(new DistributeCoins().distributeCoins(root));
    }
}
