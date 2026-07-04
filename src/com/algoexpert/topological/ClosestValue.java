package com.algoexpert.topological;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ClosestValue {

    private List<Integer> ans;
    private double target;
    private int k;

    public List<Integer> closestKValues2(TreeNode root, double target, int k) {
        Stack<TreeNode> predecessors = new Stack<>();
        Stack<TreeNode> successors = new Stack<>();

        initPredecessors(root, target, predecessors);
        initSuccessors(root, target, successors);

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (predecessors.isEmpty()) {
                result.add(getNextSuccessor(successors));
            } else if (successors.isEmpty()) {
                result.add(getNextPredecessor(predecessors));
            } else {
                double predDiff = Math.abs(predecessors.peek().val - target);
                double succDiff = Math.abs(successors.peek().val - target);

                if (predDiff <= succDiff) {
                    result.add(getNextPredecessor(predecessors));
                } else {
                    result.add(getNextSuccessor(successors));
                }
            }
        }
        return result;
    }

    private void initPredecessors(TreeNode root, double target, Stack<TreeNode> stack) {
        while (root != null) {
            if (root.val < target) {
                stack.push(root);
                root = root.right;
            } else {
                root = root.left;
            }
        }
    }

    private void initSuccessors(TreeNode root, double target, Stack<TreeNode> stack) {
        while (root != null) {
            if (root.val >= target) {
                stack.push(root);
                root = root.left;
            } else {
                root = root.right;
            }
        }
    }

    private int getNextPredecessor(Stack<TreeNode> stack) {
        TreeNode node = stack.pop();
        int result = node.val;

        node = node.left;
        while (node != null) {
            stack.push(node);
            node = node.right;
        }

        return result;
    }

    private int getNextSuccessor(Stack<TreeNode> stack) {
        TreeNode node = stack.pop();
        int result = node.val;

        node = node.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }

        return result;
    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        ans = new LinkedList<>();
        this.target = target;
        this.k = k;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (ans.size() < k) {
            ans.add(root.val);
        } else {
            if(Math.abs(root.val - target) >= Math.abs(ans.get(0) - target)) {
                return;
            }
            ans.remove(0);
            ans.add(root.val);
        }
        dfs(root.right);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(4,
                new TreeNode(2,
                        new TreeNode(1), new TreeNode(3)),
                new TreeNode(5)
        );

        ClosestValue closestValue = new ClosestValue();
        closestValue.closestKValues(root, 4.7, 2);
    }


}
