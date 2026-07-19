package com.algoexpert.topological;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NAryMerge {

    static class Node {
        String key;
        int value;
        List<Node> children;

        Node(String key, int value) {
            this.key = key;
            this.value = value;
            this.children = new ArrayList<>();
        }
    }



    public Node mergeTrees(Node root1, Node root2) {
        if(root1 == null) return root2;
        if(root2 == null) return root1;

        // Roots are different, cannot merge directly
        if (!root1.key.equals(root2.key)) {
            Node root = new Node("ROOT", 0);
            root.children.add(root1);
            root.children.add(root2);
            return root;
        }

        // Same root key: merge
        Node merged = new Node(root1.key, root1.value + root2.value);

        Map<String, Node> map = new HashMap<>();
        for(Node child: root1.children) {
            map.put(child.key, child);
        }

        for (Node child : root2.children) {
            if (map.containsKey(child.key)) {
                map.put(child.key,  mergeTrees(map.get(child.key), child));
            } else {
                map.put(child.key, child);
            }
        }
        merged.children.addAll(map.values());
        return merged;
    }

    public static void main(String[] args) {

    /*
        Tree 1:

              A:1
             /   \
          B:2     C:3
         /
       D:4


        Tree 2:

              A:5
             /   \
          B:6     E:7
    */


        Node root1 = new Node("A", 1);

        Node b1 = new Node("B", 2);
        Node c1 = new Node("C", 3);
        Node d1 = new Node("D", 4);
        b1.children.add(d1);
        root1.children.add(b1);
        root1.children.add(c1);


        Node root2 = new Node("A", 5);
        Node b2 = new Node("B", 6);
        Node e2 = new Node("E", 7);
        root2.children.add(b2);
        root2.children.add(e2);

        NAryMerge sol = new NAryMerge();
        Node mergedRoot = sol.mergeTrees(root1, root2);
        printTree(mergedRoot, 0);
    }


    private static void printTree(Node node, int level) {

        if (node == null)
            return;
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(node.key + ":" + node.value);
        for (Node child : node.children) {
            printTree(child, level + 1);
        }
    }
}
