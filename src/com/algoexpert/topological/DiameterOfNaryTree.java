package com.algoexpert.topological;

import java.util.ArrayList;
import java.util.List;

public class DiameterOfNaryTree {

    static class Node {
        int val;
        List<Node> children;

        Node(int val) {
            this.val = val;
            children = new ArrayList<>();
        }
    }

    private int diameter = 0;

    public int diameter(Node root) {
        dfs(root);
        return diameter;
    }

    private int dfs(Node node) {
        if (node == null)
            return -1; // height in terms of edges

        int longest = -1;
        int secondLongest = -1;

        for (Node child : node.children) {
            int h = dfs(child);

            if (h > longest) {
                secondLongest = longest;
                longest = h;
            } else if (h > secondLongest) {
                secondLongest = h;
            }
        }

        // Diameter passing through current node
        diameter = Math.max(diameter, longest + secondLongest + 2);

        // Return height of current node
        return longest + 1;
    }

    public static void main(String[] args) {
        Node A = new Node(1);
        Node B = new Node(2);
        Node C = new Node(3);
        Node D = new Node(4);
        Node E = new Node(5);
        Node F = new Node(6);
        Node G = new Node(7);

        A.children.add(B);
        A.children.add(C);
        A.children.add(D);

        B.children.add(E);
        B.children.add(F);

        D.children.add(G);

        DiameterOfNaryTree obj = new DiameterOfNaryTree();
        System.out.println(obj.diameter(A)); // Output: 4
    }
}
