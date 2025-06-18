package com.techiedelight.binarytree.hard;

import com.algoexpert.binarytree.view.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildTreeInorderPreOrderPostOrder {
    int preIndex = 0;
    int postIndex = 0;
    Map<Integer, Integer> inorderMap = new HashMap<>();


    public Node buildPreOrderLeafBooleanArray(int[] preorder, boolean[] isLeaf) {
        if (preorder == null || preorder.length == 0) return null;
        preIndex = 0;
        return buildUsingPreOrderLeafBoolean(preorder, isLeaf);
    }

    public Node buildUsingPreOrderLeafBoolean(int[] preorder, boolean[] isLeaf) {
        if (preIndex >= preorder.length) return null;
        Node root = new Node(preorder[preIndex]);
        if (isLeaf[preIndex++]) {
            return root;
        }
        root.left = buildPreOrderLeafBooleanArray(preorder, isLeaf);
        root.right = buildPreOrderLeafBooleanArray(preorder, isLeaf);
        return root;

    }
    //------------

    public Node buildPreOrderPostOrderTree(int[] preorder, int[] postorder) {
        Map<Integer, Integer> postIndexMap = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            postIndexMap.put(postorder[i], i);
        }

        return buildUsingPostOrderPreOrder(preorder, 0, preorder.length - 1,
                postorder, 0, postorder.length - 1, postIndexMap);
    }

    private Node buildUsingPostOrderPreOrder(int[] preorder, int preStart, int preEnd,
                                             int[] postorder, int postStart, int postEnd,
                                             Map<Integer, Integer> postIndexMap) {
        // if(postStart > postEnd || preIndex >= preorder.length)  return null;
        if (preStart > preEnd || postStart > postEnd) return null;

        // Base case: leaf node
        if (preStart == preEnd) {
            return new Node(preorder[preStart]);
        }
        Node root = new Node(preorder[preStart]);
        int leftChildVal = preorder[preStart + 1];
        int leftChildIndex = postIndexMap.get(leftChildVal);
        int leftSubTreeSize = leftChildIndex - postStart + 1;

        root.left = buildUsingPostOrderPreOrder(preorder, preStart + 1, leftSubTreeSize,
                postorder, postStart, leftChildIndex,
                postIndexMap);
        root.right = buildUsingPostOrderPreOrder(preorder, preStart + leftSubTreeSize + 1, preEnd,
                postorder, leftChildIndex - 1, postEnd - 1,
                postIndexMap);

        return root;

    }

    //------------

    public Node buildInOrderLevelOrderTree(int[] inorder, int[] levelOrder) {
        // Build a map to quickly find the index of any node in inorder[]
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return buildUsingLevelOrder(levelOrder, 0, inorder.length - 1, inorderIndexMap);
    }

    private Node buildUsingLevelOrder(int[] levelOrder, int inStart, int inEnd, Map<Integer, Integer> inorderMap) {
        if (inStart > inEnd || levelOrder.length == 0) return null;

        Node root = new Node(levelOrder[0]);

        // Base case for leaf node
        if (levelOrder.length == 1) return root;

        int rootIndex = inorderMap.get(root.data);

        List<Integer> leftSubtree = new ArrayList<>();
        List<Integer> rightSubtree = new ArrayList<>();

        for (int i = 1; i < levelOrder.length; i++) {
            int currentVal = levelOrder[i];
            int currentIndex = inorderMap.get(currentVal);

            if (currentIndex < rootIndex) {
                leftSubtree.add(currentVal);
            } else {
                // Otherwise, add to right subtree
                rightSubtree.add(currentVal);
            }
        }

        int[] leftLevelOrder = leftSubtree.stream().mapToInt(i -> i).toArray();
        int[] rightLevelOrder = rightSubtree.stream().mapToInt(i -> i).toArray();

        // Recursively build left and right subtrees
        root.left = buildUsingLevelOrder(leftLevelOrder, inStart, rootIndex - 1, inorderMap);
        root.right = buildUsingLevelOrder(rightLevelOrder, rootIndex + 1, inEnd, inorderMap);

        return root;
    }

    //------------

    public Node buildInOrderPreOrderTree(int[] preorder, int[] inorder) {
        // Map each value to its index in inorder array for O(1) lookups
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildUsingPreOrder(preorder, 0, inorder.length - 1);
    }

    private Node buildUsingPreOrder(int[] preorder, int inStart, int inEnd) {

        if (inStart > inEnd) return null;
        int rootVal = preorder[preIndex++];

        Node root = new Node(rootVal);

        int rootIndex = inorderMap.get(rootVal);

        root.left = buildUsingPreOrder(preorder, inStart, rootIndex - 1);
        root.right = buildUsingPreOrder(preorder, rootIndex + 1, inEnd);

        return root;

    }

    // ----------
    public Node buildInOrderPostOrderTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildUsingPostOrder(postorder, 0, inorder.length - 1);
    }

    private Node buildUsingPostOrder(int[] postorder, int inStart, int inEnd) {
        if (inStart > inEnd) return null;

        int rootVal = postorder[postIndex++];
        Node root = new Node(rootVal);
        // Find root index in inorder
        int rootIndex = inorderMap.get(rootVal);
        // Recur: build right subtree first, then left
        root.right = buildUsingPostOrder(postorder, rootIndex + 1, inEnd);
        root.left = buildUsingPostOrder(postorder, inStart, rootIndex - 1);

        return root;
    }
}
