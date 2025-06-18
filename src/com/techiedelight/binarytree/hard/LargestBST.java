package com.techiedelight.binarytree.hard;

import com.algoexpert.binarytree.view.Node;

public class LargestBST {

    public int largestBst(Node root) {
        if (isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            return size(root);
        }
        return Math.max(largestBst(root.left), largestBst(root.right));
    }

    public boolean isBst(Node root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if (root.data < min || root.data > max) {
            return false;
        }
        return isBst(root.left, min, root.data) && isBst(root.right, root.data, max);
    }

    public int size(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + size(root.left) + size(root.right);
    }
}
