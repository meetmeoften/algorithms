package com.techiedelight.binarytree.hard;

import com.algoexpert.binarytree.view.Node;

import java.util.ArrayList;
import java.util.List;

public class SameInorderTraversal {

    public List<Node> buildTreesFromInorder(int[] inorder) {
        return buildTrees(inorder, 0, inorder.length - 1);
    }

    private List<Node> buildTrees(int[] inorder, int start, int end) {
        List<Node> result = new ArrayList<>();

        if (start > end) {
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; i++) {
            List<Node> leftSubtrees = buildTrees(inorder, start, i - 1);
            List<Node> rightSubtrees = buildTrees(inorder, i + 1, end);

            for (Node left : leftSubtrees) {
                for (Node right : rightSubtrees) {
                    Node root = new Node(inorder[i]);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        List<Node> result = new SameInorderTraversal().buildTreesFromInorder(new int[]{1, 2, 3});
        System.out.println(result);
    }
}
