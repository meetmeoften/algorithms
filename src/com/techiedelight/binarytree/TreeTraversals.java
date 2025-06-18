package com.techiedelight.binarytree;

import com.algoexpert.binarytree.view.Node;

public class TreeTraversals {

    // TODO iterative

    public void inorder(Node node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.println(node.data);
        inorder(node.right);
    }

    public void preorder(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.data);
        preorder(node.left);
        preorder(node.right);
    }


    public int height(Node root) {
        if (root == null) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);
        return 1 + Math.max(left, right);
    }

    public boolean identical(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        return node1 != null && node2 != null &&
                node1.data == node2.data &&
                identical(node1.left, node2.left) &&
                identical(node1.right, node2.right);
    }


}
