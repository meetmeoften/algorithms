package com.techiedelight.binarytree.hard;

import com.algoexpert.binarytree.view.Node;

public class BinaryTreeToDLL {

    Node head = null;
    Node prev = null;

    void convertToDLL(Node root) {
        if (root == null) return;
        convertToDLL(root.left);
        if (prev == null) {
            head = root;
        } else {
            prev.right = root;
            root.left = prev;
        }
        prev = root; // Move prev forward
        convertToDLL(root.right);
    }

    public void printDLL(Node head) {
        System.out.println("Doubly Linked List:");
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data);
            if (curr.right != null) System.out.print(" <-> ");
            curr = curr.right;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        /*
                10
               /  \
              5    15
             /
            3
        */
        Node root = new Node(10);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(15);

        BinaryTreeToDLL converter = new BinaryTreeToDLL();
        converter.convertToDLL(root);
        converter.printDLL(converter.head);
    }
}
