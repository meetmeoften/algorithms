package com.techiedelight.binarytree.hard;

import com.algoexpert.binarytree.view.Node;

import java.util.List;
import java.util.Stack;

public class LeafTraversalChecker {


    void collectLeaves(Node root, List<Node> leavesList) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            leavesList.add(root);
        }
        collectLeaves(root.left, leavesList);
        collectLeaves(root.right, leavesList);
    }

    boolean leafTraversal(Node root1, Node root2) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root1);
        stack2.push(root2);
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int leaf1 = getNextLeaf(stack1);
            int leaf2 = getNextLeaf(stack2);
            if (leaf1 != leaf2) return false;
        }
        return stack1.isEmpty() && stack2.isEmpty();
    }

    int getNextLeaf(Stack<Node> stack) {
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node != null) {
                if (node.left == null && node.right == null) {
                    return node.data;
                }
                stack.push(node.left);
                stack.push(node.right);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Node root1 = new Node(3);
        root1.left = new Node(5);
        root1.right = new Node(1);
        root1.left.left = new Node(6);
        root1.left.right = new Node(2);
        root1.right.right = new Node(9);

        Node root2 = new Node(4);
        root2.left = new Node(5);
        root2.right = new Node(6);
        root2.left.left = new Node(6);
        root2.left.right = new Node(2);
        root2.right.right = new Node(9);

        System.out.println(new LeafTraversalChecker().leafTraversal(root1, root2)); // false
    }
}
