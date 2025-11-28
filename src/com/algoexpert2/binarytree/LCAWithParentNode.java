package com.algoexpert2.binarytree;

public class LCAWithParentNode {
    static class Node {
        int value;
        Node parent;

        Node left; // used only for main class- should not use for LCA
        Node right; // used only for main class- should not use for LCA

        Node(int value) {
            this.value = value;
        }
    }

    public static Node lowestCommonAncestor(Node a, Node b) {
        int depthA = getDepth(a);
        int depthB = getDepth(b);

        // Make depths equal
        if (depthA > depthB) {
            a = lift(a, depthA - depthB);
        } else {
            b = lift(b, depthB - depthA);
        }

        // Walk up together
        while (a != b) {
            a = a.parent;
            b = b.parent;
        }
        return a;
    }

    private static int getDepth(Node x) {
        int depth = 0;
        while (x.parent != null) {
            x = x.parent;
            depth++;
        }
        return depth;
    }

    private static Node lift(Node x, int steps) {
        while (steps-- > 0) {
            x = x.parent;
        }
        return x;
    }

    // ---------------- Main method ----------------
    public static void main(String[] args) {
        /*
               Constructing this tree:

                       1
                    /     \
                   2       3
                 /   \
                4     5
         */

        Node root = new Node(1);

        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        // Connect children
        root.left = n2;
        root.right = n3;
        n2.left = n4;
        n2.right = n5;

        // Set parent pointers
        n2.parent = root;
        n3.parent = root;
        n4.parent = n2;
        n5.parent = n2;
        // root.parent stays null

        // Example queries:
        Node lca = lowestCommonAncestor(n4, n5);
        System.out.println("LCA of 4 and 5 = " + lca.value);  // Expect 2

        Node lca2 = lowestCommonAncestor(n4, n3);
        System.out.println("LCA of 4 and 3 = " + lca2.value); // Expect 1

        Node lca3 = lowestCommonAncestor(n5, n3);
        System.out.println("LCA of 5 and 3 = " + lca3.value); // Expect 1
    }
}

