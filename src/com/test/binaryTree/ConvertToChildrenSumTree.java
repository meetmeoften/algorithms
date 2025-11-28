package com.test.binaryTree;

public class ConvertToChildrenSumTree {

    public static void convert(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return;

        // Step 1: Recursively convert subtrees
        convert(root.left);
        convert(root.right);

        // Step 2: Compute children sum
        int leftVal = (root.left != null) ? root.left.value : 0;
        int rightVal = (root.right != null) ? root.right.value : 0;
        int childSum = leftVal + rightVal;

        if (childSum >= root.value) {
            // Increase root to match children
            root.value = childSum;
        } else {
            // Increase children to match root
            pushDown(root, root.value - childSum);
        }
    }

    // Push parent's valueue down to children if needed
    private static void pushDown(TreeNode node, int parentVal) {
        if (node.left != null) {
            node.left.value += parentVal;
            convert(node.left);
        } else if (node.right != null) {
            node.right.value += parentVal;
            convert(node.right);
        }
    }

    // Optional: inorder display
    public static void  printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.value + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        /*
              50
             /  \
            7    2
           / \    \
          3   5    1
        */

        TreeNode root = new TreeNode(50);
        root.left = new TreeNode(7);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(1);

        convert(root);

        printInorder(root);  // Converted tree will satisfy children-sum property
    }
}
