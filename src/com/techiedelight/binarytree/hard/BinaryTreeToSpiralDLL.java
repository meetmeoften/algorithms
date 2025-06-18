package com.techiedelight.binarytree.hard;

import com.algoexpert.binarytree.view.Node;

import java.util.Deque;
import java.util.LinkedList;

public class BinaryTreeToSpiralDLL {

    public Node convertToSpiralDLL(Node root) {
        if (root == null) return null;
        Deque<Node> deque = new LinkedList<>();
        deque.add(root);
        boolean leftToRight = true;
        Node head = null, prev = null;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                Node curr;
                if (leftToRight) {
                    curr = deque.pollFirst();
                    if (curr.left != null) deque.addLast(curr.left);
                    if (curr.right != null) deque.addLast(curr.right);
                } else {
                    curr = deque.pollLast();
                    if (curr.right != null) deque.addFirst(curr.right);
                    if (curr.left != null) deque.addFirst(curr.left);
                }
                // Build DLL
                if (prev == null) {
                    head = curr;
                } else {
                    prev.right = curr;
                    curr.left = prev;
                }
                prev = curr;
            }
            leftToRight = !leftToRight; // flip direction
        }
        return head;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(7);
        root.left.right = new Node(6);
        root.right.left = new Node(5);
        root.right.right = new Node(4);

        BinaryTreeToSpiralDLL converter = new BinaryTreeToSpiralDLL();
        Node head = converter.convertToSpiralDLL(root);
    }
}
