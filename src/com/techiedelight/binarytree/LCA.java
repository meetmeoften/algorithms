package com.techiedelight.binarytree;

import com.algoexpert.binarytree.view.Node;

public class LCA {

    public static Node lca(Node root, Integer node1, Integer node2) {
        if (root == null) {
            return null;
        }
        if (root.data == node1 || root.data == node2) {
            return root;
        }
        Node left = lca(root.left, node1, node2);
        Node right = lca(root.right, node1, node2);

        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }

    public static int distance(Node root, int target) {
        if (root == null) {
            return -1;
        }
        if (root.data == target) {
            return 0;
        }
        int leftDist = distance(root.left, target);
        if (leftDist != -1) {
            return leftDist + 1;
        }
        int rightDist = distance(root.right, target);
        if (rightDist != -1) {
            return rightDist + 1;
        }
        return -1;
    }


    public static int distanceBetweenNodes(Node root, int n1, int n2) {
        Node lca = lca(root, n1, n2);
        if (lca == null) return -1;

        int d1 = distance(lca, n1);
        int d2 = distance(lca, n2);

        return d1 + d2;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
//        root.left.right = new Node(5);
//        root.right.left = new Node(6);
//        root.right.right = new Node(7);

        int n1 = 4, n2 = 3;
        System.out.println("Distance between " + n1 + " and " + n2 + " is: " +
                distanceBetweenNodes(root, n1, n2));
    }

    public boolean isSubTree(Node root, Node node1) {
        return isSameTree(root, node1) || isSubTree(root.left, node1) || isSubTree(root.right, node1);
    }

    public boolean isSameTree(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        return node1 != null && node2 != null &&
                node1.data == node2.data &&
                isSameTree(node1.left, node2.left) &&
                isSameTree(node1.right, node2.right);
    }
}
