package com.techiedelight.binarytree.hard;

import com.algoexpert.binarytree.view.Node;

public class FixBST {

    Node first = null, middle = null, prev = null;

    public void fixBST(Node root) {
        inorder(root);
        // Fix the swapped nodes
        if (first != null && middle != null) {
            swap(first, middle);
        }
    }

    public void inorder(Node root) {
        if (root == null) return;

        inorder(root.left);
        // Detect disorder
        if (prev != null && root.data < prev.data) {
            if (first == null) {
                // First inversion
                first = prev;
                middle = root;
            } else {
                // Second inversion
                middle = root;
            }
        }
        prev = root;
        inorder(root.right);
    }

    private void swap(Node a, Node b) {
        int temp = a.data;
        a.data = b.data;
        b.data = temp;
    }

    // For testing: print inorder
    public void printInorder(Node root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        /*
            Input Tree (Swapped BST):
                    3
                   / \
                  1   4
                     /
                    2
            Swapped nodes: 2 and 3
        */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(4);
        //root.right.left = new Node(2);

        FixBST fixer = new FixBST();

        System.out.print("Before fixing (Inorder): ");
        fixer.printInorder(root);
        System.out.println();

        fixer.fixBST(root);

        System.out.print("After fixing (Inorder): ");
        fixer.printInorder(root);
    }
}
