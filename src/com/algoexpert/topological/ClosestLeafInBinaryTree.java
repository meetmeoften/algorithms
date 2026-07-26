package com.algoexpert.topological;



import com.neetcode.binaryTree.TreeNode;

import java.util.*;

public class ClosestLeafInBinaryTree {


    Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
    TreeNode target;

    public int findClosestLeaf(TreeNode root, int k) {
        buildGraph(root, null, k);
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);
        while (!queue.isEmpty()) {

            TreeNode node = queue.poll();

            // First leaf reached
            if (node.left == null && node.right == null) {
                return node.val;
            }

            for (TreeNode next : graph.getOrDefault(node, new ArrayList<>())) {

                if (visited.add(next)) {
                    queue.offer(next);
                }
            }
        }

        return -1;
    }

    private void graph(TreeNode node, TreeNode parent, int k) {
        if(node == null) {
            return;
        }

        if(node.val == k) {
            target = node;
        }

        graph.putIfAbsent(node, new ArrayList<>());

        if(parent != null) {
            graph.get(node).add(parent);
            graph.get(parent).add(node);
        }

        buildGraph(node.left, node, k);;
        buildGraph(node.right, node, k);
    }

    private void buildGraph(TreeNode node, TreeNode parent, int k) {
        if (node == null) {
            return;
        }
        if (node.val == k) {
            target = node;
        }

        graph.putIfAbsent(node, new ArrayList<>());

        if (parent != null) {
            graph.get(node).add(parent);
            graph.get(parent).add(node);
        }

        buildGraph(node.left, node, k);
        buildGraph(node.right, node, k);
    }

    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node5 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);

        rootNode.left = node2;
        rootNode.right = node5;

        ClosestLeafInBinaryTree closestValue = new ClosestLeafInBinaryTree();
        //closestValue.closestKValues(root, 3.7, 2);
        closestValue.findClosestLeaf(rootNode, 1);
    }
}
