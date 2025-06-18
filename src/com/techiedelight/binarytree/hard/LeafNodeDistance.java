package com.techiedelight.binarytree.hard;

import com.algoexpert.binarytree.view.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeafNodeDistance {

    void leafNodeDistance(Node node, int dist) {
        List<Node> path = new ArrayList<>();

        // create an empty set to store distinct nodes at a given
        // distance from leaf nodes
        Set<Node> set = new HashSet<>();

        // find all nodes
        leafNodeDistance(node, path, set, dist);

        // print output
        for (Node e : set) {
            System.out.print(e.data + " ");
        }
    }

    void leafNodeDistance(Node node, List<Node> path, Set<Node> set, int dist) {
        if (node == null) {
            return;
        }
        if (isLeaf(node) && path.size() >= dist) {
            set.add(path.get(path.size() - dist));
            return;
        }
        path.add(node);
        leafNodeDistance(node.left, path, set, dist);
        leafNodeDistance(node.right, path, set, dist);
        path.remove(node);
    }

    boolean isLeaf(Node node) {
        return (node.left == null && node.right == null);
    }

    public static void main(String[] args) {
        Node root = new Node(15);
        root.left = new Node(10);
        root.right = new Node(20);
        root.left.left = new Node(8);
        root.left.right = new Node(12);

        int dist = 1;
        new LeafNodeDistance().leafNodeDistance(root, dist);
    }
}
