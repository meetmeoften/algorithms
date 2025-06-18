package com.techiedelight.binarytree.hard;

import java.util.LinkedList;
import java.util.Queue;

public class LinkNodes {

    public static class Node {
        int val;
        Node left, right, next;

        Node(int val) {
            this.val = val;
        }
    }

    public void connectUsingQueue(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);


        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Node prev = null;

            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();

                if (prev != null) {
                    prev.next = current;
                }
                prev = current;
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
    }


    public void connect(Node root) {
        if (root == null) {
            return;
        }
        // Start with the topmost node
        Node levelStart = root;

        while (levelStart != null) {
            // Dummy node acts as a temporary head for the next level's linked list
            Node dummyHead = new Node(0);
            Node current = levelStart;
            Node prev = dummyHead;

            // Iterate through current level and connect next level nodes
            while (current != null) {
                // Connect left child
                if (current.left != null) {
                    prev.next = current.left;
                    prev = prev.next;
                }

                // Connect right child
                if (current.right != null) {
                    prev.next = current.right;
                    prev = prev.next;
                }

                // Move to the next node in current level
                current = current.next;
            }

            // Move to the first node of next level
            levelStart = dummyHead.next;
        }
    }

    // Print the next pointers level by level
    public void printLevels(Node root) {
        while (root != null) {
            Node current = root;
            while (current != null) {
                System.out.print(current.val + " -> ");
                current = current.next;
            }
            System.out.println("null");

            // Move to leftmost node of next level
            if (root.left != null) root = root.left;
            else if (root.right != null) root = root.right;
            else root = root.next;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        LinkNodes solution = new LinkNodes();
        solution.connect(root);
        solution.printLevels(root);
    }
}
