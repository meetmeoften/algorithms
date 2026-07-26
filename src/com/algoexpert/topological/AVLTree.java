package com.algoexpert.topological;

public class AVLTree {

    static class Node {
        int key;
        int height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    Node root;

    int height(Node node) {
        return node == null ? 0 : node.height;
    }

    int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }

    Node insert(Node node, int key) {

        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        // RR
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // LR
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void inorder(Node node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.key + " ");
        inorder(node.right);
    }


    public static void main(String[] args) {

        AVLTree tree = new AVLTree();

        int[] values = {10, 20, 30, 40, 50, 25};

        for (int value : values) {
            tree.root = tree.insert(tree.root, value);
        }

        System.out.print("Inorder Traversal: ");
        tree.inorder(tree.root);
    }
}
