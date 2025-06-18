package com.techiedelight.binarytree;

import com.algoexpert.binarytree.view.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RootToLeafPath {

    public void printRootToLeafPaths(Node node) {
        List<Integer> path = new ArrayList<>();
        dfs(node, path);
    }
    
    private void dfs(Node node, List<Integer> path) {
        if (node == null) return;
        // Add current node to path
        path.add(node.data);
        // If it's a leaf, print the path
        if (node.left == null && node.right == null) {
            System.out.println(path);
        }
        // Recur for left and right subtrees
        dfs(node.left, path);
        dfs(node.right, path);
        // Backtrack: remove current node before going back
        path.remove(path.size() - 1);
    }

    public void printRootToLeafPathsBFS(Node root) {
        if (root == null) {
            return;
        }
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, String.valueOf(root.data)));
        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            Node node = current.node;
            String path = current.path;
            // If it's a leaf, print the path
            if (node.left == null && node.right == null) {
                System.out.println(path);
            }
            // Add left and right children to the queue with updated path
            if (node.left != null) {
                queue.add(new Pair(node.left, path + " -> " + node.left.data));
            }
            if (node.right != null) {
                queue.add(new Pair(node.right, path + " -> " + node.right.data));
            }
        }
    }

    static class Pair {
        Node node;
        String path;

        Pair(Node node, String path) {
            this.node = node;
            this.path = path;
        }
    }

}
