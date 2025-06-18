package com.techiedelight.binarytree.hard;

import com.algoexpert.binarytree.view.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class InvertBinaryTree {

    public void swapAlternateLevels1(Node root) {
        if (root == null || root.left == null || root.right == null) return;

        Queue<NodePair> queue = new LinkedList<>();
        queue.add(new NodePair(root.left, root.right));
        boolean shouldSwap = false; // Track whether to swap this level

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Node> levelNodes = new ArrayList<>();

            // Collect all nodes at the current level
            for (int i = 0; i < size; i++) {
                NodePair pair = queue.poll();
                Node left = pair.left;
                Node right = pair.right;

                if (shouldSwap) {
                    int temp = left.data;
                    left.data = right.data;
                    right.data = temp;
                }
                if (left.left != null && right.right != null)
                    queue.add(new NodePair(left.left, right.right));
                if (left.right != null && right.left != null)
                    queue.add(new NodePair(left.right, right.left));
            }
            shouldSwap = !shouldSwap;
        }
    }

    // Helper class to store mirror node pairs
    static class NodePair {
        Node left, right;

        NodePair(Node l, Node r) {
            left = l;
            right = r;
        }
    }

    // recursive
    public void swapAlternateLevels(Node root) {
        if (root == null) {
            return;
        }
    }

    public void swapLevels(Node left, Node right, int level) {
        if (left == null || right == null) return;

        // Swap the left and right
        if (level % 2 == 1) {
            Integer temp = left.data;
            left.data = right.data;
            right.data = temp;
        }

        // Recurse on left and right subtrees
        swapLevels(left.left, right.right, level + 1);
        swapLevels(left.right, right.left, level + 1);
    }

    public Node invertTree(Node root) {
        if (root == null) return null;

        // Swap the left and right
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Recurse on left and right subtrees
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

}
