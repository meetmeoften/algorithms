package com.techiedelight.binarytree;

import com.algoexpert.binarytree.view.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    // TODO recursive level order traversal

    public void levelOrderIterative(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Integer size = q.size();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                System.out.println(node.data);
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
        }
    }

    public void spiralOrderRecursive(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, 0, result);
    }

    public void dfs(Node node, int level, List<List<Integer>> result) {
        if (node == null) {
            return;
        }
        if (result.size() <= level) {
            result.add(new ArrayList<>());
        }
        if (level % 2 == 0) {
            result.get(level).add(node.data);
        } else {
            result.get(level).add(0, node.data);
        }
        dfs(node.left, level + 1, result);
        dfs(node.right, level + 1, result);
    }
}
