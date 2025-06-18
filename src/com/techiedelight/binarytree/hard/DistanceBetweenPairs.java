package com.techiedelight.binarytree.hard;

import com.algoexpert.binarytree.view.Node;

public class DistanceBetweenPairs {

    public int findDistanceBetweenNodes(Node root, int n1, int n2) {
        Node lca = findLCA(root, n1, n2);
        if (lca == null) {
            return -1;
        }
        int d1 = findDistance(lca, n1, 0);
        int d2 = findDistance(lca, n2, 0);
        return d1 + d2;
    }

    public int findDistance(Node root, int target, int level) {
        if (root == null) {
            return -1;
        }

        if (root.data == target) {
            return level;
        }

        int left = findDistance(root.left, target, level + 1);
        if (left != -1) {
            return left;
        }

        return findDistance(root.right, target, level + 1);
    }

    public Node findLCA(Node root, Integer n1, Integer n2) {
        if (root == null) {
            return null;
        }
        if (root.data == n1 || root.data == n2) {
            return root;
        }
        Node left = findLCA(root.left, n1, n2);
        Node right = findLCA(root.right, n1, n2);

        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}
