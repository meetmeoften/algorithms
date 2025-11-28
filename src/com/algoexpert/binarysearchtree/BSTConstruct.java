package com.algoexpert.binarysearchtree;

class BST {

    // Node class
    class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    // -----------------------------
    // INSERT
    // -----------------------------
    public void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < node.value) {
            node.left = insertRec(node.left, value);
        } else if (value > node.value) {
            node.right = insertRec(node.right, value);
        }
        return node; // unchanged if duplicate
    }

    // -----------------------------
    // SEARCH
    // -----------------------------
    public boolean search(int value) {
        return searchRec(root, value);
    }

    private boolean searchRec(Node node, int value) {
        if (node == null) return false;
        if (value == node.value) return true;

        return value < node.value
                ? searchRec(node.left, value)
                : searchRec(node.right, value);
    }

    // -----------------------------
    // REMOVE
    // -----------------------------
    public void remove(int value) {
        root = removeRec(root, value);
    }

    private Node removeRec(Node node, int value) {
        if (node == null) return null;

        if (value < node.value) {
            node.left = removeRec(node.left, value);
        } else if (value > node.value) {
            node.right = removeRec(node.right, value);
        } else {
            // Node found
            // Case 1: no child
            if (node.left == null && node.right == null) {
                return null;
            }
            // Case 2: one child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Case 3: two children → find inorder successor
            Node successor = minValueNode(node.right);
            node.value = successor.value;
            node.right = removeRec(node.right, successor.value);
        }
        return node;
    }

    private Node minValueNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // -----------------------------
    // TRAVERSALS (optional)
    // -----------------------------
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.value + " ");
            inorderRec(node.right);
        }
    }

    public void preorder() {
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preorderRec(node.left);
            preorderRec(node.right);
        }
    }

    public void postorder() {
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(Node node) {
        if (node != null) {
            postorderRec(node.left);
            postorderRec(node.right);
            System.out.print(node.value + " ");
        }
    }

    // -----------------------------
    // MAIN (usage example)
    // -----------------------------
    public static void main(String[] args) {
        BST tree = new BST();

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("In-order:");
        tree.inorder();

        System.out.println("Search 40: " + tree.search(40));
        System.out.println("Remove 50");
        tree.remove(50);

        System.out.println("In-order after removal:");
        tree.inorder();
    }
}

