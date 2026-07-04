package com.algoexpert.topological;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NArrayTreeMerge {


    static class Node {
        String key;
        int value;
        List<Node> children;

        public Node(String key, int value) {
            this.key = key;
            this.value = value;
            this.children = new ArrayList<>();
        }
    }
    public Node merge(Node a, Node b) {
        if(a == null) return cloneTree(b);
        if(b == null )return cloneTree(a);

        Node merged = new Node(a.key, a.value + b.value);
        Map<String, Node> map = new HashMap<>();
        for(Node child: a.children) {
            map.put(child.key, cloneTree(child));
        }
        for(Node childB: b.children) {
            if(map.containsKey(childB.key)) {
                Node childA = map.get(childB.key);
                map.put(childB.key, merge(childA, childB));
            } else {
                map.put(childB.key, cloneTree(childB));
            }
        }
        merged.children.addAll(map.values());
        return merged;
    }

    private Node cloneTree(Node node) {
        if(node == null) return null;

        Node copy = new Node(node.key, node.value);
        for(Node child: node.children) {
            copy.children.add(cloneTree(child));
        }
        return copy;
    }

    public void printTree(Node root, int level) {
        if (root == null) return;
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(root.key + ":" + root.value);
        for (Node child : root.children) {
            printTree(child, level + 1);
        }
    }

    public static void main(String[] args) {

        // Tree 1
        Node root1 = new Node("Root", 10);

        Node A1 = new Node("A", 2);
        Node B1 = new Node("B", 2);
        Node M1 = new Node("M", 6);

        A1.children.add(new Node("L", 2));
        B1.children.add(new Node("L", 2));

        root1.children.add(A1);
        root1.children.add(B1);
        root1.children.add(M1);

        // Tree 2
        Node root2 = new Node("Root", 13);

        Node A2 = new Node("A", 4);
        Node B2 = new Node("B", 3);
        Node D2 = new Node("D", 6);

        A2.children.add(new Node("L", 4));

        Node M2 = new Node("M", 6);
        D2.children.add(M2);

        root2.children.add(A2);
        root2.children.add(B2);
        root2.children.add(D2);

        // Merge
        NArrayTreeMerge sol = new NArrayTreeMerge();

        Node merged = sol.merge(root1, root2);

        // Print result
        sol.printTree(merged, 0);
    }
}
