package com.techiedelight.binarytree;

import com.algoexpert.binarytree.view.Node;

public class DiameterOfBinaryTree {
    Node root;
    int diameter = 0;

    public int diameterOfBinaryTree(Node root) {
        height(root);
        return diameter;
    }

    // Helper function to compute height and update diameter
    private int height(Node node) {
        if (node == null) return 0;

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        // Diameter through current node = left height + right height
        diameter = Math.max(diameter, leftHeight + rightHeight);

        // Return height of this subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        DiameterOfBinaryTree tree = new DiameterOfBinaryTree();

        // Sample tree
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
//        tree.root.left.left = new Node(4);
//        tree.root.left.right = new Node(5);

        System.out.println("Diameter of the binary tree: " + tree.diameterOfBinaryTree(tree.root));
    }


}
