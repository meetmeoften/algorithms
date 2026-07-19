package com.algoexpert.topological;


import com.algoexpert.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class FindLeavesOfBinaryTree {

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private int dfs(TreeNode node, List<List<Integer>> result ) {
        if(node == null) {
            return -1;
        }
        int left  = dfs(node.left, result);
        int right = dfs(node.right, result);
        int height = 1  + Math.max(left, right);
        if(result.size() == height) {
            result.add(new ArrayList<>());
        }
        result.get(height).add(node.val);
        return height;
    }

}
