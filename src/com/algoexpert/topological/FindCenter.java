package com.algoexpert.topological;

import com.neetcode.binaryTree.TreeNode;

import java.util.*;

public class FindCenter {

    public TreeNode findCenter(TreeNode root) {

        // Convert binary tree to graph
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        buildGraph(root, null, graph);

        // Find one end of diameter
        TreeNode start = bfs(root, graph, new HashMap<>());

        // Find other end and parent map
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        TreeNode end = bfsWithParent(start, graph, parent);

        // Build diameter path
        List<TreeNode> path = new ArrayList<>();

        TreeNode curr = end;

        while (curr != null) {
            path.add(curr);
            curr = parent.get(curr);
        }

        // Middle node
        return path.get(path.size() / 2);
    }


    private void buildGraph(TreeNode node,
                            TreeNode parent,
                            Map<TreeNode, List<TreeNode>> graph) {

        if (node == null)
            return;

        graph.putIfAbsent(node, new ArrayList<>());

        if (parent != null) {
            graph.get(node).add(parent);
            graph.get(parent).add(node);
        }

        buildGraph(node.left, node, graph);
        buildGraph(node.right, node, graph);
    }


    private TreeNode bfs(TreeNode start,
                         Map<TreeNode, List<TreeNode>> graph,
                         Map<TreeNode, TreeNode> parent) {

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        TreeNode last = start;

        while (!queue.isEmpty()) {
            last = queue.poll();

            for (TreeNode next : graph.get(last)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }

        return last;
    }


    private TreeNode bfsWithParent(TreeNode start,
                                   Map<TreeNode, List<TreeNode>> graph,
                                   Map<TreeNode, TreeNode> parent) {

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);
        parent.put(start, null);

        TreeNode last = start;

        while (!queue.isEmpty()) {
            last = queue.poll();

            for (TreeNode next : graph.get(last)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    parent.put(next, last);
                    queue.offer(next);
                }
            }
        }

        return last;
    }
}
