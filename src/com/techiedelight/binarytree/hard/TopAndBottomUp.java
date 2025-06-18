package com.techiedelight.binarytree.hard;

import com.algoexpert.binarytree.view.Node;

import java.util.*;

public class TopAndBottomUp {


    public void printNodeBottomUp(Node root) {
        if (root == null) {
            return;
        }
        int level = 1;
        Map<Integer, List<Integer>> map = new HashMap<>();


        Queue<Node> q1 = new ArrayDeque<>(), q2 = new ArrayDeque<>();

        if (root.left != null && root.right != null) {
            q1.add(root.left);
            q2.add(root.right);
        }

        while (!q1.isEmpty()) {
            level++;
            int n = q1.size();

            while (n-- > 0) {
                Node x = q1.poll();
                map.putIfAbsent(level, new ArrayList<>());
                map.get(level).add(x.data);
                if (x.left != null) {
                    q1.add(x.left);
                }

                if (x.right != null) {
                    q1.add(x.right);
                }

                Node y = q2.poll();
                map.get(level).add(y.data);
                if (y.right != null) {
                    q2.add(y.right);
                }
                if (y.left != null) {
                    q2.add(y.left);
                }
            }
            for (int i = map.size(); i > 0; i--) {
                System.out.print(map.get(i));
            }
        }
    }

    // This is a perfect Binary Tree
    public void printNodesTopView(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        Queue<Node> first = new ArrayDeque<>();
        Queue<Node> second = new ArrayDeque<>();

        if (root.left != null && root.right != null) {
            first.add(root.left);
            second.add(root.right);
        }

        while (!first.isEmpty()) {
            int n = first.size();
            while (n-- > 0) {
                Node x = first.poll();
                System.out.print(x.data + " ");
                if (x.left != null) {
                    first.add(x.left);
                }
                if (x.right != null) {
                    first.add(x.right);
                }

                // dequeue front node from the second queue and print it
                Node y = second.poll();
                System.out.print(y.data + " ");
                // enqueue right and left child of `y` to the second queue
                if (y.right != null) {
                    second.add(y.right);
                }
                if (y.left != null) {
                    second.add(y.left);
                }
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        new TopAndBottomUp().printNodeBottomUp(root);
        new TopAndBottomUp().printNodesTopView(root);
    }


}
