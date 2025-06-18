package com.techiedelight.binarytree.hard;

import com.algoexpert.binarytree.view.Node;

public class SinkNodes {

    public void sinkNodes(Node root) {

        if(root == null) {
            return;
        }

        sinkNodes(root.left);
        sinkNodes(root.right);

        if(root.data == 0) {
            sink(root);
        }
    }

    private void sink(Node root) {
        if(root == null) {
            return;
        }
        if(root.left != null && root.left.data !=0) {
            int temp = root.data;
            root.data = root.left.data;
            root.left.data = temp;

            // recur for the left subtree
            sink(root.left);
        } else if(root.right != null && root.right.data !=0) {
            int temp = root.data;
            root.data = root.right.data;
            root.right.data = temp;

            // recur for the right subtree
            sink(root.right);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(0);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.right = new Node(0);
        //root.right.left.left = new Node(3);
        //root.right.left.right = new Node(4);

        SinkNodes sinkNodes = new SinkNodes();
        sinkNodes.sinkNodes(root);

    }
}
