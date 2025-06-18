package com.techiedelight.binarytree;

import com.algoexpert.binarytree.view.Node;

public class PrintAncestors {

    Node root;

    public boolean printAncestors(Node root, int target) {
        if (root == null) {
            return false;
        }
        if (root.data == target) {
            return true;
        }
        if (printAncestors(root.left, target) || printAncestors(root.right, target)) {
            System.out.println(root.data);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        PrintAncestors tree = new PrintAncestors();

        // Sample binary tree
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);

        int target = 5;
        System.out.print("Ancestors of node " + target + ": ");
        boolean found = tree.printAncestors(tree.root, target);

        if (!found) {
            System.out.println("Node not found in the tree.");
        }

    }
}
